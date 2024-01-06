package com.example.adminpage.service;

import com.example.adminpage.model.entity.PartnerCompany;
import com.example.adminpage.model.network.Header;
import com.example.adminpage.model.network.request.PartnerApiRequest;
import com.example.adminpage.model.network.response.PartnerApiResponse;
import org.springframework.stereotype.Service;

@Service
public class PartnerApiLogicService extends BaseService<PartnerApiRequest, PartnerApiResponse, PartnerCompany> {
    @Override
    public Header<PartnerApiResponse> create(Header<PartnerApiRequest> request) {
        return null;
    }

    @Override
    public Header<PartnerApiResponse> read(Long id) {
        return baseRepository.findById(id)
                .map(partnerCompany -> response(partnerCompany))
                .orElseGet(() -> Header.ERROR("데이터 없음"));
    }

    @Override
    public Header<PartnerApiResponse> update(Header<PartnerApiRequest> request) {
        return null;
    }

    @Override
    public Header delete(Long id) {
        return null;
    }

    private Header<PartnerApiResponse> response(PartnerCompany partnerCompany) {
        PartnerApiResponse body = PartnerApiResponse.builder()
                .id(partnerCompany.getId())
                .name(partnerCompany.getName())
                .status(partnerCompany.getStatus())
                .address(partnerCompany.getAddress())
                .callCenter(partnerCompany.getCallCenter())
                .partnerNumber(partnerCompany.getPartnerNumber())
                .businessNumber(partnerCompany.getBusinessNumber())
                .ceoName(partnerCompany.getCeoName())
                .registeredAt(partnerCompany.getRegisteredAt())
                .unregisteredAt(partnerCompany.getUnregisteredAt())
                .categoryId(partnerCompany.getId())
                .build();

        return Header.OK(body);
    }
}
