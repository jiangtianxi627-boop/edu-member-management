package com.xq.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xq.pojo.AdminRoleMenus;
import com.xq.service.IAdminRoleMenusService;
import com.xq.utils.DataResults;
import com.xq.utils.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author xq
 * @since 2025-06-02
 */
@RestController
@RequestMapping("/admin-role-menus")
public class AdminRoleMenusController {


    @Autowired
    IAdminRoleMenusService adminRoleMenusService;

    @GetMapping("queryByRoleId/{roleId}")
    public DataResults queryByRoleId(@PathVariable("roleId") Integer roleId){
        QueryWrapper<AdminRoleMenus> query = new QueryWrapper<>();
        query.eq("roleId",roleId);
        return DataResults.success(ResultCode.SUCCESS,adminRoleMenusService.list(query));
    }
}
