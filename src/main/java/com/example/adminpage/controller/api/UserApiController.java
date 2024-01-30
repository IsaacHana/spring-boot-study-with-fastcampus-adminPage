package com.example.adminpage.controller.api;

import com.example.adminpage.controller.CrudController;
import com.example.adminpage.model.entity.User;
import com.example.adminpage.model.network.Header;
import com.example.adminpage.model.network.request.UserApiRequest;
import com.example.adminpage.model.network.response.UserApiResponse;
import com.example.adminpage.model.network.response.UserOrderInfoApiResponse;
import com.example.adminpage.service.UserApiLogicService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/user")
public class UserApiController extends CrudController<UserApiRequest, UserApiResponse, User> {

    private final UserApiLogicService userApiLogicService;

    @GetMapping("/{id}/orderInfo")
    public Header<UserOrderInfoApiResponse> orderInfo(@PathVariable Long id,
                                                      @PageableDefault(size = 5)
                                                      @SortDefault(sort = "orderAt", direction = Sort.Direction.DESC)
                                                      Pageable pageable
    ) {
        return userApiLogicService.orderInfo(id, pageable);
    }

    @GetMapping()
    public Header<List<UserApiResponse>> search(@PageableDefault(sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
//        log.info("{}", pageable);
        return userApiLogicService.search(pageable);
    }
}
