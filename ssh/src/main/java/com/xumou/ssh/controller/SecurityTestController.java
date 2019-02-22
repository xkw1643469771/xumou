package com.xumou.ssh.controller;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 */

@Api(value = "权限控制", description = "Spring Security")
@RequestMapping("security")
@RestController
public class SecurityTestController {

    @GetMapping("test")
    public String test(){
        return "SUCCESS";
    }

}