package com.xumou.ssh.controller;

import com.xumou.ssh.entity.Role;
import com.xumou.ssh.entity.User;
import com.xumou.ssh.repository.RoleRepository;
import com.xumou.ssh.repository.UserRepository;
import com.xumou.ssh.service.UserRoleTestService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.UUID;

/**
 *
 */
@RestController
@RequestMapping("userRoleTest")
public class UserRoleTestController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserRoleTestService userRoleTestService;

    @GetMapping("insertUserByJpa")
    public Object insertUserByJpa(){
        Random r = new Random();
        User user = new User();
        user.setAge((short) r.nextInt(100));
        user.setName(UUID.randomUUID().toString());
        user.setIdCard(UUID.randomUUID().toString());
        userRepository.save(user);
        return null;
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "角色名称",paramType = "query", dataType = "String", required = true),
            @ApiImplicitParam(name = "userIds", value = "关联用户id", paramType = "query")
    })
    @ApiOperation("插入角色, 如果有用户id, 则关联")
    @GetMapping("insertRole")
    public Object insertRole(String name, Long[] userIds){
        Role role = new Role();
        role.setName(name);
        if(userIds != null){
            List<User> users = userRepository.findAllById(Arrays.asList(userIds));
            role.setUsers(users);
        }
        roleRepository.save(role);
        return "插入成功!";
    }


    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "用户名", dataType = "String", paramType = "query")
    })
    @ApiOperation("插入用户")
    @GetMapping("insertUser")
    public Object insertUser(String name){
        User user = new User();
        user.setName(name);
        userRepository.save(user);
        return "插入成功!";
    }

    @ApiImplicitParams(
            @ApiImplicitParam(name = "id",value = "用户id", paramType = "query", required = true)
    )
    @ApiOperation("查询用户")
    @GetMapping("selectUser")
    public Object selectUser(Long id){
        User one = userRepository.getOne(id);
        String str = one.getName();
        for (Role role : one.getRoles()) {
            str += "," + role.getName();
        }
        return str;
    }

    @ApiImplicitParams(
            @ApiImplicitParam(name = "id", value = "角色id", paramType = "query", required = true)
    )
    @ApiOperation("根据id查询角色")
    @GetMapping("selectRole")
    public Object selectRole(Long id){
        Role one = roleRepository.getOne(id);
        String str = one.getName();
        for (User user : one.getUsers()) {
            str += "," + user.getName();
        }
        return str;
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户id", paramType = "query", required = true),
            @ApiImplicitParam(name = "name", value = "用户名称", paramType = "query", required = true),
            @ApiImplicitParam(name = "roleIds", value = "角色id集合", paramType = "query", required = true)
    })
    @ApiOperation("更新用户")
    @GetMapping("updateUser")
    public Object updateUser(Long id, String name, Long[] roleIds){
        User user = userRepository.getOne(id);
        System.out.println(user.getName());
        user.setName(name);
        user.setRoles(roleRepository.findAllById(Arrays.asList(roleIds)));
        User one = userRepository.getOne(id);
        String str = one.getName();
        for (Role role : one.getRoles()) {
            str += "," + role.getName();
        }
        return str;
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "角色id", paramType = "query", required = true),
            @ApiImplicitParam(name = "name", value = "角色名称", paramType = "query", required = true),
    })
    @ApiOperation("更新角色")
    @GetMapping("updateRole")
    public Object updateRole(Long id, String name){
        Role one = roleRepository.getOne(id);
        one.setName(name);
        return one.getId()+" "+one.getName();
    }

    @ApiOperation("测试业务层jpa")
    @GetMapping("testService")
    public Object testService(){
        userRoleTestService.testService();
        return "";
    }


    @ApiOperation("测试 JpaSpecificationExecutor 接口")
    @GetMapping("testJpaSpecificationExecutor")
    public Object testJpaSpecificationExecutor(){
        return roleRepository.findAll(new Specification<Role>() {
            public Predicate toPredicate(Root<Role> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                Expression<Long> x = root.join("users", JoinType.LEFT).get("id").as(Long.class);
                Predicate leftJoinUser = criteriaBuilder.equal(x, "");
                Predicate in = root.get("id").in(1, 2);
                return criteriaBuilder.and(leftJoinUser, in);
            }
        });
    }
}