package com.xumou.sys.html;

import com.xumou.sys.html.po.Catalog;
import com.xumou.sys.util.LogUtils;
import com.xumou.sys.util.TimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 *
 */
@RestController
@RequestMapping("html")
public class HtmlController {

    @Autowired
    private HtmlService htmlService;

    @GetMapping("test1")
    public List<String> test1(){
        return htmlService.findHtmlFile();
    }

    @GetMapping("catalog")
    public Catalog catalog(String parentPath){
        TimeUtils.onceTimer();
        Catalog catelogs = htmlService.getCatelogs(parentPath);
        LogUtils.info(parentPath + " 用时: " + TimeUtils.onceTimer() + "毫秒");
        return catelogs;
    }

}