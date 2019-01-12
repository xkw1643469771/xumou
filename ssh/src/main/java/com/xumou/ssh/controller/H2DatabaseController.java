package com.xumou.ssh.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xumou.ssh.service.H2DatabaseService;

@Api(value = "H2数据库")
@RestController
public class H2DatabaseController {
	
	@Autowired
	private H2DatabaseService h2DatabaseService;
	
	@ApiOperation("测试大批量插入性能通过save")
	@GetMapping("batchInsert")
	public Object batchInsert(){
		h2DatabaseService.batchInsert();
		return "插入成功";
	}
	
	@ApiOperation("测试大批量插入性能通过saveAll")
	@GetMapping("batchInsertByList")
	public Object batchInsertByList(){
		h2DatabaseService.batchInsertByList();
		return "插入成功";
	}
	
	@ApiOperation("测试JdbcTemplate批量插入性能")
	@GetMapping("batchInsertByJdbcTemplate")
	public Object batchInsertByJdbcTemplate(){
		h2DatabaseService.batchInsertByJdbcTemplate();
		return "插入成功";
	}
	
}
