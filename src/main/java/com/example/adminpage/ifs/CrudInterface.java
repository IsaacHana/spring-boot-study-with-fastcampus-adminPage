package com.example.adminpage.ifs;

import com.example.adminpage.model.network.Header;

public interface CrudInterface {
    Header create(); // TODO : request object 추가

    Header read(Long id);

    Header update();

    Header delete(Long id);
}
