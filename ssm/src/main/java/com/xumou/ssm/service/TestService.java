package com.xumou.ssm.service;

import com.xumou.ssm.entity.Test;
import com.xumou.ssm.entity.TestExample;
import com.xumou.ssm.mapper.TestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 */
@Service
public class TestService {

    @Autowired
    private TestMapper testMapper;

    public Object select() {
        TestExample example = new TestExample();
        return testMapper.selectByExample(example);
    }
}