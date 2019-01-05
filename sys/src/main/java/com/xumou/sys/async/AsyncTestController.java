package com.xumou.sys.async;

import com.xumou.sys.util.TimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 */
@RestController
@RequestMapping("async")
public class AsyncTestController {

    @Autowired
    private AsyncTestService testService;

    // 测试异步调用方法
    @GetMapping("test1")
    public String test1(){
        TimeUtils.onceTimer();
        testService.test1();
        testService.test1();
        testService.test1();
        testService.test1();
        return "" + TimeUtils.onceTimer();
    }

}