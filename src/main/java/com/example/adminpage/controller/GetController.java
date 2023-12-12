package com.example.adminpage.controller;

import com.example.adminpage.model.SearchParam;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api") // localhost:8080/api/
public class GetController {

    @RequestMapping(method = RequestMethod.GET, path = "/getMethod") // localhost:8080/api/getMethod
    public String getRequest() {

        return "Hi getMethod";
    }

    @GetMapping("/getParameter") // localhost:8080/api/getParameter?id=1234&password=abcd
    public String getParameter(@RequestParam(name = "id", value = "") String id, @RequestParam(name = "password", value = "") String pwd) {
        String password = "bbbb";
        System.out.println("id : " + id);
        System.out.println("password : " + pwd);

        return id + pwd;
    }

    // localhost:8080/api/getMultiParameter?account=abcd&email=study@gmail.com&page=10
    // 더 많은 파라미터가 있다면?
    @GetMapping("/getMultiParameter")
    public SearchParam getMultiParameter(SearchParam searchParam) {
        System.out.println(searchParam.getAccount());
        System.out.println(searchParam.getEmail());
        System.out.println(searchParam.getPage());

        // 네트워크 통신 : json
        // { "account" : "", "email" : "", "page" : 0 }
        // SpringBoot 에서 jackson 이라는 라이브러리를 통해 클래스 보고 json으로 변환시켜준다.
        return searchParam;
    }
}
