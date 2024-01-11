package com.example.adminpage.controller.api;

import com.example.adminpage.controller.CrudController;
import com.example.adminpage.model.entity.Item;
import com.example.adminpage.model.network.Header;
import com.example.adminpage.model.network.request.ItemApiRequest;
import com.example.adminpage.model.network.response.ItemApiResponse;
import com.example.adminpage.service.ItemApiLogicService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/item")
@RequiredArgsConstructor
public class ItemApiController extends CrudController<ItemApiRequest, ItemApiResponse, Item> {

    private final ItemApiLogicService itemApiLogicService;

    @GetMapping()
    public Header<List<ItemApiResponse>> search(@PageableDefault(sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        return itemApiLogicService.search(pageable);
    }
}
