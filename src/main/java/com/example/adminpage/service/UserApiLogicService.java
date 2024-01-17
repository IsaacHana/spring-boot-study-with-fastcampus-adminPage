package com.example.adminpage.service;

import com.example.adminpage.model.entity.OrderGroup;
import com.example.adminpage.model.entity.User;
import com.example.adminpage.model.enumclass.UserStatus;
import com.example.adminpage.model.network.Header;
import com.example.adminpage.model.network.Pagination;
import com.example.adminpage.model.network.request.UserApiRequest;
import com.example.adminpage.model.network.response.OrderDetailApiResponse;
import com.example.adminpage.model.network.response.OrderGroupApiResponse;
import com.example.adminpage.model.network.response.UserApiResponse;
import com.example.adminpage.model.network.response.UserOrderInfoApiResponse;
import com.example.adminpage.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserApiLogicService extends BaseService<UserApiRequest, UserApiResponse, User> {

    private final UserRepository userRepository;

    private final OrderGroupApiLogicService orderGroupApiLogicService;
    private final OrderDetailApiLogicService orderDetailApiLogicService;

    private final ItemApiLogicService itemApiLogicService;

    // 1. request data
    // 2. user 생성
    // 3. 생성된 데이터 -> UserApiResponse return
    @Override
    public Header<UserApiResponse> create(UserApiRequest request) {
        // 2. User 생성
        User user = User.builder()
                .account(request.getAccount())
                .password(request.getPassword())
                .status(UserStatus.REGISTERED)
                .phoneNumber(request.getPhoneNumber())
                .email(request.getEmail())
                .registeredAt(LocalDateTime.now())
                .build();

        User newUser = baseRepository.save(user);

        // 3. 생성된 데이터 -> userApiResponse return
        return Header.OK(response(newUser));
    }

    @Override
    public Header<UserApiResponse> read(Long id) {
        // id -> repository getOne, getById
        // user -> userApiResponse return
        return baseRepository.findById(id).map(user -> Header.OK(response(user)))
                .orElseGet(() -> Header.ERROR("데이터 없음"));
    }

    @Override
    public Header<UserApiResponse> update(UserApiRequest request) {
        // 1. data
        log.info("request : {}", request);
        // 2. id -> user 데이터 찾기
        Optional<User> optional = baseRepository.findById(request.getId());

        return optional.map(user -> {
                    // 3. update
                    // id
                    user.setAccount(request.getAccount())
                            .setPassword(request.getPassword())
                            .setPhoneNumber(request.getPhoneNumber())
                            .setEmail(request.getEmail())
                            .setRegisteredAt(request.getRegisteredAt())
                            .setUnregisteredAt(request.getUnregisteredAt());
                    return user;
                })
                .map(user -> baseRepository.save(user)) // update
                .map(user -> Header.OK(response(user)))            // userApiResponse
                .orElseGet(() -> Header.ERROR("데이터 없음"));

    }

    @Override
    public Header delete(Long id) {
        // 1. id -> repository -> user
        Optional<User> optional = baseRepository.findById(id);
        // 2. repository -> delete

        return optional.map(user -> {
                    baseRepository.delete(user);
                    return Header.OK();
                })
                .orElseGet(() -> Header.ERROR("데이터 없음"));
    }

    private UserApiResponse response(User user) {
        // user -> userApiResponse

        return UserApiResponse.builder()
                .id(user.getId())
                .account(user.getAccount())
                .password(user.getPassword()) // TODO: 암호화, 길이
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .status(user.getStatus())
                .registeredAt(user.getRegisteredAt())
                .unregisteredAt(user.getUnregisteredAt())
                .build();
    }

    public Header<List<UserApiResponse>> search(Pageable pageable) {
        Page<User> users = baseRepository.findAll(pageable);

        List<UserApiResponse> userApiResponses = users.stream()
                .map(user -> response(user))
                .toList();
        // List<UserApiResponse>
        // Header<List<UserApiResponse>>

        Pagination pagination = Pagination.builder()
                .totalPages(users.getTotalPages())
                .totalElements(users.getTotalElements())
                .currentElements(users.getNumber())
                .currentPage(users.getNumberOfElements())
                .currentSize(users.getSize())
                .build();

        return Header.OK(userApiResponses, pagination);
    }

    public Header<UserOrderInfoApiResponse> orderInfo(Long id) {
        // user
        User user = userRepository.getReferenceById(id);
        UserApiResponse userApiResponse = response(user);

        // orderGroup
        List<OrderGroup> orderGroups = user.getOrderGroups();
        List<OrderGroupApiResponse> orderGroupApiResponses = orderGroups.stream()
                .map(orderGroup -> {
                            OrderGroupApiResponse orderGroupApiResponse = orderGroupApiLogicService.response(orderGroup);
                            // orderDetail api response
                            List<OrderDetailApiResponse> orderDetailApiResponses = orderGroup.getOrderDetails().stream()
                                    .map(orderDetail -> orderDetailApiLogicService.response(orderDetail)).toList();

                            orderGroupApiResponse.setOrderDetailApiResponses(orderDetailApiResponses);
                            return orderGroupApiResponse;
                        }
                ).toList();

        userApiResponse.setOrderGroupApiResponses(orderGroupApiResponses);

        UserOrderInfoApiResponse userOrderInfoApiResponse = UserOrderInfoApiResponse.builder()
                .userOrderInfo(userApiResponse)
                .build();

        return Header.OK(userOrderInfoApiResponse);
    }
}
