package com.example.adminpage.service;

import com.example.adminpage.model.entity.Item;
import com.example.adminpage.model.network.Header;
import com.example.adminpage.model.network.Pagination;
import com.example.adminpage.model.network.request.ItemApiRequest;
import com.example.adminpage.model.network.response.ItemApiResponse;
import com.example.adminpage.repository.PartnerCompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemApiLogicService extends BaseService<ItemApiRequest, ItemApiResponse, Item> {
    @Autowired
    private PartnerCompanyRepository partnerCompanyRepository;

    @Override
    public Header<ItemApiResponse> create(ItemApiRequest request) {

        Item item = Item.builder()
                .status(request.getStatus())
                .name(request.getName())
                .title(request.getTitle())
                .content(request.getContent())
                .price(request.getPrice())
                .brandName(request.getBrandName())
                .registeredAt(request.getRegisteredAt())
                .partnerCompany(partnerCompanyRepository.getReferenceById(request.getPartnerId()))
                .build();

        Item newItem = baseRepository.save(item);

        return Header.OK(response(newItem));
    }

    @Override
    public Header<ItemApiResponse> read(Long id) {

        return baseRepository.findById(id)
                .map(item -> Header.OK(response(item)))
                .orElseGet(() -> Header.ERROR("데이터 없음"));
    }

    @Override
    public Header<ItemApiResponse> update(ItemApiRequest request) {

        return baseRepository.findById(request.getId())
                .map(entityItem -> {
                    entityItem
                            .setStatus(request.getStatus())
                            .setName(request.getName())
                            .setTitle(request.getTitle())
                            .setContent(request.getContent())
                            .setPrice(request.getPrice())
                            .setBrandName(request.getBrandName())
                            .setRegisteredAt(request.getRegisteredAt())
                            .setUnregisteredAt(request.getUnregisteredAt());

                    return entityItem;
                })
                .map(newEntityItem -> baseRepository.save(newEntityItem))
                .map(newEntityItem -> Header.OK(response(newEntityItem)))
                .orElseGet(() -> Header.ERROR("데이터 없음"));
    }

    @Override
    public Header delete(Long id) {

        return baseRepository.findById(id)
                .map(item -> {
                    baseRepository.delete(item);

                    return Header.OK();
                })
                .orElseGet(() -> Header.ERROR("데이터 없음"));
    }

    public Header<List<ItemApiResponse>> search(Pageable pageable) {
        Page<Item> items = baseRepository.findAll(pageable);

        List<ItemApiResponse> itemApiResponses = items.stream()
                .map(this::response)
                .toList();

        Pagination pagination = Pagination.builder()
                .totalPages(items.getTotalPages())
                .totalElements(items.getTotalElements())
                .currentPage(items.getNumberOfElements())
                .currentElements(items.getNumber())
                .currentSize(items.getSize())
                .build();

        return Header.OK(itemApiResponses, pagination);
    }

    public ItemApiResponse response(Item item) {
        return ItemApiResponse.builder()
                .id(item.getId())
                .status(item.getStatus())
                .name(item.getName())
                .title(item.getTitle())
                .content(item.getContent())
                .price(item.getPrice())
                .brandName(item.getBrandName())
                .registeredAt(item.getRegisteredAt())
                .unregisteredAt(item.getUnregisteredAt())
                .partnerId(item.getPartnerCompany().getId())
                .build();
    }


}
