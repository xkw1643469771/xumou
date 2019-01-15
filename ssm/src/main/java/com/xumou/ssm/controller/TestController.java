package com.xumou.ssm.controller;

import com.xumou.ssm.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 */
@RequestMapping("test")
@RestController
public class TestController {

    @Autowired
    public TestService testService;

    @GetMapping("select")
    public Object select(){
        return testService.select();
    }

}