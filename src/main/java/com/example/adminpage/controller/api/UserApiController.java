package com.example.adminpage.controller.api;

import com.example.adminpage.ifs.CrudInterface;
import com.example.adminpage.model.network.Header;
import com.example.adminpage.model.network.request.UserApiRequest;
import com.example.adminpage.model.network.response.UserApiResponse;
import com.example.adminpage.service.UserApiLogicService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j // lombok logging
@RestController
@RequestMapping("/api/user")
public class UserApiController implements CrudInterface<UserApiRequest, UserApiResponse> {
    @Autowired
    private UserApiLogicService userApiLogicService;

    @Override
    @PostMapping("") // api/user
    public Header<UserApiResponse> create(@RequestBody Header<UserApiRequest> request) {
        /*
        {
            "transaction_time":"2023-12-19T22:57:56.69",
                "result_code":"OK",
                "description":"OK",
                "data":{
            "account" : "CreateTest01",
                    "password" : "CreateTest01",
                    "status" : "UNREGISTERED",
                    "email" : "CreateTest01@test.com",
                    "phone_number":"010-1234-5678"
        }
        }
        */
        log.info("{}", request); // 디버깅 할때 유용함
        return userApiLogicService.create(request);
    }

    @Override
    @GetMapping("{id}") // /api/user/{id}
    public Header<UserApiResponse> read(@PathVariable(name = "id") Long id) {
        return null;
    }

    @Override
    @PutMapping("")
    public Header<UserApiResponse> update(@RequestBody Header<UserApiRequest> request) {
        return null;
    }

    
    @Override
    @DeleteMapping("{id}") // api/user/{id}
    public Header<UserApiResponse> delete(@PathVariable Long id) {
        return null;
    }
}
