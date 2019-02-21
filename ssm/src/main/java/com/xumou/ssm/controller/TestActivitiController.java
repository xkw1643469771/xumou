package com.xumou.ssm.controller;

import com.xumou.ssm.service.TestActivitiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 */
@RequestMapping("testActiviti")
@RestController
public class TestActivitiController {

    @Autowired
    private TestActivitiService testActivitiService;

    @GetMapping("testProcessEngine")
    public void testProcessEngine(){
        testActivitiService.testProcessEngine();
    }

}