package com.example.adminpage.controller.api;

import com.example.adminpage.controller.CrudController;
import com.example.adminpage.model.entity.PartnerCompany;
import com.example.adminpage.model.network.Header;
import com.example.adminpage.model.network.request.PartnerApiRequest;
import com.example.adminpage.model.network.response.PartnerApiResponse;
import com.example.adminpage.service.PartnerApiLogicService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/partner")
@RequiredArgsConstructor
public class PartnerApiController extends CrudController<PartnerApiRequest, PartnerApiResponse, PartnerCompany> {
    private final PartnerApiLogicService partnerApiLogicService;

    @GetMapping()
    public Header<List<PartnerApiResponse>> search(@PageableDefault(sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        return partnerApiLogicService.search(pageable);
    }
}
