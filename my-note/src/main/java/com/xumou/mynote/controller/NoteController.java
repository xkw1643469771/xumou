package com.xumou.mynote.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 */
@RequestMapping("note")
@Controller
public class NoteController {

    @PostMapping("isStart")
    @ResponseBody
    public boolean isStart(){
        return true;
    }

    @GetMapping
    @ResponseBody
    public String a(){
        return "success";
    }

}