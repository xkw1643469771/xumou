package com.xumou.sys.schedule;

import com.xumou.sys.util.LogUtils;
import com.xumou.sys.util.TimeUtils;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 *
 */
@Service
public class ScheduleTestService {

    @Async
    public void test1(){
        TimeUtils.sleep(2000);
        LogUtils.info("定时任务1");
    }

}