package com.crm.web.controller;

import com.crm.bean.Role;
import com.crm.enums.ExceptionEnums;
import com.crm.service.RoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/role")
@Api(value = "角色管理",tags = {"角色管理"})
public class RoleController {
    @Autowired
    private RoleService roleService;


    @GetMapping("/selectById")
    @ApiOperation("根据id查询角色")
    public ResponseEntity selectById(Long id) {
        Role role = roleService.findById(id);
        return ResponseEntity.ok(role);
    }

    @GetMapping("/getAll")
    @ApiOperation("查询所有角色")
    public ResponseEntity selectAll() {
        return ResponseEntity.ok(roleService.findAllRole());
    }

    @PostMapping("/save")
    @ApiOperation("新增角色")
    public ResponseEntity save(@RequestBody  Role role) {
        roleService.savaOrUpdate(role);
        return ResponseEntity.ok(ExceptionEnums.SUCCESS);
    }

    @PostMapping("/update")
    @ApiOperation("修改角色")
    public ResponseEntity update(@RequestBody  Role role) {
        roleService.savaOrUpdate(role);
        return ResponseEntity.ok(ExceptionEnums.SUCCESS);
    }

    @GetMapping("/delete")
    @ApiOperation("删除角色")
    public ResponseEntity delete(Long id) {
        roleService.deleteById(id);
        return ResponseEntity.ok(ExceptionEnums.SUCCESS);
    }

}


