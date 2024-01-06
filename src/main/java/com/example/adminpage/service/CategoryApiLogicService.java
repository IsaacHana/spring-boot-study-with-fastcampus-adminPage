package com.example.adminpage.service;

import com.example.adminpage.model.entity.Category;
import com.example.adminpage.model.network.Header;
import com.example.adminpage.model.network.request.CategoryApiRequest;
import com.example.adminpage.model.network.response.CategoryApiResponse;
import org.springframework.stereotype.Service;

@Service
public class CategoryApiLogicService extends BaseService<CategoryApiRequest, CategoryApiResponse, Category> {

    @Override
    public Header<CategoryApiResponse> create(Header<CategoryApiRequest> request) {
        return null;
    }

    @Override
    public Header<CategoryApiResponse> read(Long id) {
        return null;
    }

    @Override
    public Header<CategoryApiResponse> update(Header<CategoryApiRequest> request) {
        return null;
    }

    @Override
    public Header delete(Long id) {
        return null;
    }
}
