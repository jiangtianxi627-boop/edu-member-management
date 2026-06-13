package com.xq.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xq.pojo.AdminUserRole;
import com.xq.service.IAdminUserRoleService;
import com.xq.utils.DataResults;
import com.xq.utils.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author xq
 * @since 2025-05-25
 */
@RestController
@RequestMapping("/admin-user-role")
public class AdminUserRoleController {

    @Autowired
    IAdminUserRoleService adminUserRoleService;

    @GetMapping("queryByAdminId/{adminId}")
    public DataResults queryByAdminId(@PathVariable("adminId") Integer adminId){
        QueryWrapper<AdminUserRole> query = new QueryWrapper<>();
        query.eq("adminId",adminId);
        List<AdminUserRole> list =
                adminUserRoleService.list(query);
        return DataResults.success(ResultCode.SUCCESS,list);
    }
}
