package com.example.adminpage.controller.api;

import com.example.adminpage.controller.CrudController;
import com.example.adminpage.model.entity.OrderGroup;
import com.example.adminpage.model.network.Header;
import com.example.adminpage.model.network.request.OrderGroupApiRequest;
import com.example.adminpage.model.network.response.OrderGroupApiResponse;
import com.example.adminpage.service.OrderGroupApiLogicService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/order-group")
@RequiredArgsConstructor
public class OrderGroupApiController extends CrudController<OrderGroupApiRequest, OrderGroupApiResponse, OrderGroup> {
    private final OrderGroupApiLogicService orderGroupApiLogicService;

    @GetMapping()
    public Header<List<OrderGroupApiResponse>> search(@PageableDefault(sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        return orderGroupApiLogicService.search(pageable);
    }
}
