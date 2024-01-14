package com.example.adminpage.service;

import com.example.adminpage.model.entity.PartnerCompany;
import com.example.adminpage.model.network.Header;
import com.example.adminpage.model.network.Pagination;
import com.example.adminpage.model.network.request.PartnerApiRequest;
import com.example.adminpage.model.network.response.PartnerApiResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PartnerApiLogicService extends BaseService<PartnerApiRequest, PartnerApiResponse, PartnerCompany> {
    @Override
    public Header<PartnerApiResponse> create(PartnerApiRequest request) {
        return null;
    }

    @Override
    public Header<PartnerApiResponse> read(Long id) {
        return baseRepository.findById(id)
                .map(partnerCompany -> Header.OK(response(partnerCompany)))
                .orElseGet(() -> Header.ERROR("데이터 없음"));
    }

    @Override
    public Header<PartnerApiResponse> update(PartnerApiRequest request) {
        return null;
    }

    @Override
    public Header delete(Long id) {
        return null;
    }

    private PartnerApiResponse response(PartnerCompany partnerCompany) {
        return PartnerApiResponse.builder()
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
    }

    public Header<List<PartnerApiResponse>> search(Pageable pageable) {
        Page<PartnerCompany> partnerCompanies = baseRepository.findAll(pageable);

        List<PartnerApiResponse> partnerApiResponses = partnerCompanies.stream()
                .map(this::response)
                .toList();

        Pagination pagination = Pagination.builder()
                .totalPages(partnerCompanies.getTotalPages())
                .totalElements(partnerCompanies.getTotalElements())
                .currentPage(partnerCompanies.getNumberOfElements())
                .currentElements(partnerCompanies.getNumber())
                .currentSize(partnerCompanies.getSize())
                .build();

        return Header.OK(partnerApiResponses, pagination);
    }
}
