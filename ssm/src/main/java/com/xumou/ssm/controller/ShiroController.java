package com.xumou.ssm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 */
@Controller
public class ShiroController {

    @GetMapping("/login")
    public String login(){
        return "/html/login.html";
    }

}