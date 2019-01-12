package com.xumou.ssh.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import springfox.documentation.annotations.ApiIgnore;

/**
 * 页面跳转控制
 */
@ApiIgnore
@Controller
public class PageController {
	
	@GetMapping("/")
	public String index(){
		return "redirect:/swagger-ui.html";
	}
	
}
