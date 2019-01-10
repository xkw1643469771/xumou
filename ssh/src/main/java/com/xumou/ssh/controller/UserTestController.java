package com.xumou.ssh.controller;

import com.xumou.ssh.entity.User;
import com.xumou.ssh.service.UserTestService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 */
@Api(value = "用户操作")
@RestController
@RequestMapping("userTest")
public class UserTestController {

    @Autowired
    private UserTestService userTestService;

    @PostMapping("selectUserByParam")
    @ApiOperation("@Query绑定参数")
    public Object selectUserByParam(@ApiParam("用户实体") @RequestBody User user){
        return userTestService.selectUserByParam(user);
    }

}