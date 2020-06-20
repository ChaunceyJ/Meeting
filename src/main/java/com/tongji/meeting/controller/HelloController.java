package com.tongji.meeting.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 *所有请求通过我们自己的Filter
 *访问401和login不需要权限认证
 *访问其他请求头部应当设置"Authorization" = "1"（放在客户端的session key），有效时间为2天
 *认证不通过则，统一报错：401 Unauthorized
 * api统一格式为"/api/XXX"
 */

@RestController
public class HelloController {

    @RequestMapping("/api/hello")
    public String index() {
        return "Hello World";
    }

    @RequestMapping("/401")
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseEntity unauthorized() {
        return new ResponseEntity("Unauthorized", HttpStatus.UNAUTHORIZED);
    }

    @RequestMapping(value = "/test" , method = RequestMethod.GET ,produces = "application/json")
    public ResponseEntity test(
            @RequestParam(value = "date")
                    Date title
    ){
        System.out.println(title);
        return ResponseEntity.ok(title);
    }
}