package com.xumou.ssh.controller;

import com.xumou.ssh.entity.Role;
import com.xumou.ssh.service.RoleTestService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 */
@RestController
@RequestMapping("roleTest")
public class RoleTestController {

    @Autowired
    private RoleTestService roleTestService;

    @GetMapping("insertRole")
    @ApiOperation("插入一个角色")
    @ApiImplicitParam(name = "roleName",value = "角色名", paramType = "query", required = true)
    public Object insertRole(String roleName){
        Role role = new Role();
        role.setName(roleName);
        return roleTestService.insertRole(role);
    }

    @GetMapping("selectSingleTable")
    @ApiOperation("单表复杂查询")
    public Object selectSingleTable(){
        return roleTestService.selectSingleTable();
    }

    @GetMapping("selectJoinMoreTable")
    @ApiOperation("多表复杂查询")
    public Object selectJoinMoreTable(){
        return roleTestService.selectJoinMoreTable();
    }

    @GetMapping("selectMoreTableBySub")
    @ApiOperation("多表子查询Exists")
    public Object selectMoreTableBySub(){
        return roleTestService.selectMoreTableBySub();
    }

}