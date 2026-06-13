package com.xq.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xq.pojo.Adminmenus;
import com.xq.service.IAdminmenusService;
import com.xq.utils.DataResults;
import com.xq.utils.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import javax.management.Query;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author xq
 * @since 2025-06-02
 */
@RestController
@RequestMapping("/adminmenus")
public class AdminmenusController {

    @Autowired
    IAdminmenusService adminmenusService;

    // 展示菜单列表
    @GetMapping("/list")
    public DataResults list(){
        List<Adminmenus> adminmenusList = adminmenusService.list(new QueryWrapper<Adminmenus>().
                eq("type", 0).eq("del", 0));
        return DataResults.success(ResultCode.SUCCESS,adminmenusList);
    }
}
