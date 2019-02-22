package com.xumou.ssm.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 */
@Controller
public class ShiroController {

    @GetMapping("/login")
    public String login(){
        return "/html/login.html";
    }

    @GetMapping("/shiro/test")
    @RequiresPermissions("test")
    public @ResponseBody String test(){
        return "success";
    }

}