package com.example.adminpage.ifs;

import com.example.adminpage.model.network.Header;

public interface CrudInterface<Req, Res> {
    Header<Res> create(Header<Req> request); // TODO: 제네릭 타입 공부하기

    Header<Res> read(Long id);

    Header<Res> update(Header<Req> request);

    Header<Res> delete(Long id);
}
