package com.xumou.ssm.service;

import org.activiti.engine.ProcessEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 */
@Service
public class TestActivitiService {

    @Autowired
    private ProcessEngine processEngine;

    public void testProcessEngine(){
        processEngine.getTaskService();
    }
}