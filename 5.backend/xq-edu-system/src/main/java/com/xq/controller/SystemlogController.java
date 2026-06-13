package com.xq.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xq.pojo.Systemlog;
import com.xq.service.ISystemlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author xq
 * @since 2025-06-02
 */
@RestController
@RequestMapping("/systemlog")
public class SystemlogController {

    @Autowired
    ISystemlogService systemlogService;

    // 分页展示日志信息
    @GetMapping("queryPage")
    public Map<String,Object> queryPage(Integer pageSize,Integer pageNumber){
        Map<String,Object> resultMap = new HashMap<>();
        // 组装查询条件
        QueryWrapper<Systemlog> q = new QueryWrapper<>();
        // 掉用mybatis-plus的分页插件
        IPage<Systemlog> page = systemlogService.page(new Page<Systemlog>(pageNumber, pageSize), q);
        resultMap.put("total",page.getTotal());
        resultMap.put("rows",page.getRecords());
        return resultMap;
    }

}
