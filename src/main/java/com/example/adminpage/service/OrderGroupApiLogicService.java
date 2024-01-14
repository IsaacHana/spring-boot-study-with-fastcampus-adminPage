package com.example.adminpage.service;

import com.example.adminpage.model.entity.OrderGroup;
import com.example.adminpage.model.network.Header;
import com.example.adminpage.model.network.Pagination;
import com.example.adminpage.model.network.request.OrderGroupApiRequest;
import com.example.adminpage.model.network.response.OrderGroupApiResponse;
import com.example.adminpage.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderGroupApiLogicService extends BaseService<OrderGroupApiRequest, OrderGroupApiResponse, OrderGroup> {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Header<OrderGroupApiResponse> create(OrderGroupApiRequest request) {
        OrderGroup orderGroup = OrderGroup.builder()
                .status(request.getStatus())
                .orderType(request.getOrderType())
                .revAddress(request.getRevAddress())
                .revName(request.getRevName())
                .paymentType(request.getPaymentType())
                .totalPrice(request.getTotalPrice())
                .totalQuantity(request.getTotalQuantity())
                .orderAt(LocalDateTime.now())
                .user(userRepository.getReferenceById(request.getUserId()))
                .build();

        OrderGroup newOrderGroup = baseRepository.save(orderGroup);

        return Header.OK(response(newOrderGroup));
    }

    @Override
    public Header<OrderGroupApiResponse> read(Long id) {

        return baseRepository.findById(id)
                .map(orderGroup -> Header.OK(response(orderGroup)))
                .orElseGet(() -> Header.ERROR("데이터 없음"));
    }

    @Override
    public Header<OrderGroupApiResponse> update(OrderGroupApiRequest request) {

        return baseRepository.findById(request.getId())
                .map(orderGroup -> {
                    orderGroup
                            .setStatus(request.getStatus())
                            .setOrderType(request.getOrderType())
                            .setRevAddress(request.getRevAddress())
                            .setRevName(request.getRevName())
                            .setPaymentType(request.getPaymentType())
                            .setTotalPrice(request.getTotalPrice())
                            .setTotalQuantity(request.getTotalQuantity())
                            .setOrderAt(request.getOrderAt())
                            .setArrivalDate(request.getArrivalDate())
                            .setUser(userRepository.getOne(request.getUserId()))
                    ;

                    return orderGroup;
                })
                .map(changeOrderGroup -> baseRepository.save(changeOrderGroup))
                .map(newOrderGroup -> Header.OK(response(newOrderGroup)))
                .orElseGet(() -> Header.ERROR("데이터 없음"));
    }

    @Override
    public Header delete(Long id) {

        return baseRepository.findById(id)
                .map(orderGroup -> {
                    baseRepository.delete(orderGroup);
                    return Header.OK();
                })
                .orElseGet(() -> Header.ERROR("데이터 없음"));
    }

    public OrderGroupApiResponse response(OrderGroup orderGroup) {

        return OrderGroupApiResponse.builder()
                .id(orderGroup.getId())
                .status(orderGroup.getStatus())
                .orderType(orderGroup.getOrderType())
                .revAddress(orderGroup.getRevAddress())
                .revName(orderGroup.getRevName())
                .paymentType(orderGroup.getPaymentType())
                .totalPrice(orderGroup.getTotalPrice())
                .totalQuantity(orderGroup.getTotalQuantity())
                .orderAt(orderGroup.getOrderAt())
                .arrivalDate(orderGroup.getArrivalDate())
                .userId(orderGroup.getUser().getId())
                .build();
    }


    public Header<List<OrderGroupApiResponse>> search(Pageable pageable) {
        Page<OrderGroup> orderGroups = baseRepository.findAll(pageable);

        List<OrderGroupApiResponse> orderGroupApiResponses = orderGroups.stream()
                .map(this::response)
                .toList();

        Pagination pagination = Pagination.builder()
                .totalPages(orderGroups.getTotalPages())
                .totalElements(orderGroups.getTotalElements())
                .currentPage(orderGroups.getNumberOfElements())
                .currentElements(orderGroups.getNumber())
                .currentSize(orderGroups.getSize())
                .build();

        return Header.OK(orderGroupApiResponses, pagination);
    }
}
