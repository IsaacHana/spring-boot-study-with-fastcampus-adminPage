package com.example.adminpage.service;

import com.example.adminpage.model.entity.OrderDetail;
import com.example.adminpage.model.network.Header;
import com.example.adminpage.model.network.request.OrderDetailApiRequest;
import com.example.adminpage.model.network.response.OrderDetailApiResponse;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderDetailApiLogicService extends BaseService<OrderDetailApiRequest, OrderDetailApiResponse, OrderDetail> {

    @Override
    public Header<OrderDetailApiResponse> create(OrderDetailApiRequest request) {
        OrderDetail orderDetail = OrderDetail.builder()
                .id(request.getId())
                .status(request.getStatus())
                .arrivalDate(request.getArrivalDate())
                .quantity(request.getQuantity())
                .totalPrice(request.getTotalPrice())
                .build();

        OrderDetail newOrderDetail = baseRepository.save(orderDetail);

        return Header.OK(response(newOrderDetail));
    }

    @Override
    public Header<OrderDetailApiResponse> read(Long id) {
        return baseRepository.findById(id)
                .map(orderDetail -> Header.OK(response(orderDetail)))
                .orElseGet(() -> Header.ERROR("데이터 없음"));
    }

    @Override
    public Header<OrderDetailApiResponse> update(OrderDetailApiRequest request) {
        Optional<OrderDetail> orderDetail = baseRepository.findById(request.getId());

        return orderDetail.map(od -> {
                            od.setStatus(request.getStatus());
                            od.setArrivalDate(request.getArrivalDate());
                            od.setQuantity(request.getQuantity());
                            od.setTotalPrice(request.getTotalPrice());
                            return od;
                        }
                ).map(od -> baseRepository.save(od))
                .map(od -> Header.OK(response(od)))
                .orElseGet(() -> Header.ERROR("데이터 없음"))
                ;
    }

    @Override
    public Header delete(Long id) {
        Optional<OrderDetail> orderDetail = baseRepository.findById(id);

        return orderDetail.map(od -> {
            baseRepository.delete(od);
            return Header.OK();
        }).orElseGet(() -> Header.ERROR("데이터 없음"));
    }

    public OrderDetailApiResponse response(OrderDetail orderDetail) {
        return OrderDetailApiResponse.builder()
                .id(orderDetail.getId())
                .status(orderDetail.getStatus())
                .quantity(orderDetail.getQuantity())
                .totalPrice(orderDetail.getTotalPrice())
                .arrivalDate(orderDetail.getArrivalDate())
                .build();
    }
}
