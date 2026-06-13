package com.xq.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xq.pojo.Loos;
import com.xq.service.ILoosService;
import com.xq.utils.DataResults;
import com.xq.utils.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author xq
 * @since 2025-05-17
 */
@RestController
@RequestMapping("/loos")
public class LoosController {

    @Autowired
    ILoosService loosService;

    /**
     * 分页查询遗失物品数据
     * @param loosName
     * @param pageSize
     * @param pageNumber
     * @return
     */
    @GetMapping("queryPage")
    public Map<String,Object> loosName(String loosName,Integer pageSize,Integer pageNumber){
        Map<String,Object> resultMap = new HashMap<>();
        QueryWrapper<Loos> q = new QueryWrapper<>();
        q.like(StringUtils.isNotEmpty(loosName),"loosName",loosName);
        q.eq("del",0); // 查询没有逻辑删除的记录
        IPage<Loos> page = loosService.page(new Page<Loos>(pageNumber, pageSize), q);
        resultMap.put("total",page.getTotal());
        resultMap.put("rows",page.getRecords());
        return resultMap;
    }

    /**
     * 新增遗失物品
     * @param loos
     * @return
     */
    @PostMapping("add")
    public DataResults add(Loos loos){
        loos.setDel(0);
        loos.setAdmin("匿名");
        boolean result = loosService.save(loos);
        if(result){
            return DataResults.success(ResultCode.SUCCESS);
        }else{
            return DataResults.success(ResultCode.FAIL);
        }
    }

    /**
     * 查看领走的物品详情信息
     * @param id
     * @return
     */
    @GetMapping("queryById/{id}")
    public DataResults queryById(@PathVariable("id") Integer id){
        Loos loos = loosService.getById(id);
        return DataResults.success(ResultCode.SUCCESS,loos);
    }

    /**
     * 取回遗失物品操作
     * @param loos
     * @return
     */
    @PutMapping("update")
    public DataResults update(Loos loos){
        loos.setAdmin("匿名");
        boolean result = loosService.updateById(loos);
        if(result){
            return DataResults.success(ResultCode.SUCCESS);
        }else{
            return DataResults.success(ResultCode.FAIL);
        }
    }
}
