package com.xq.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xq.pojo.Coach;
import com.xq.service.ICoachService;
import com.xq.utils.DataResults;
import com.xq.utils.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author xq
 * @since 2025-05-14
 */
@RestController
@RequestMapping("/coach")
public class CoachController {

    @Autowired
    ICoachService coachService;

    /**
     * 查询所有教师信息的列表
     * @return
     */
    @GetMapping("list")
    public DataResults list(){
        List<Coach> coachList = coachService.list(new QueryWrapper<Coach>().eq("del", 0));
        return DataResults.success(ResultCode.SUCCESS,coachList);
    }

    /**
     * 分页查询教师信息
     * @param coachName
     * @param pageSize
     * @param pageNumber
     * @return
     */
    @GetMapping("queryPage")
    public Map<String,Object> queryPage(String coachName,Integer pageSize,Integer pageNumber){
        Map<String,Object> resultMap = new HashMap<>();
        // 组装查询条件
        QueryWrapper<Coach> q = new QueryWrapper<>();
        q.like(StringUtils.isNotEmpty(coachName),"coachName",coachName);
        q.eq("del",0);
        // 调用mybatis-plus的分页查询进行分页查询操作
        IPage<Coach> page = coachService.page(new Page<Coach>(pageNumber, pageSize), q);
        resultMap.put("total",page.getTotal());
        resultMap.put("rows",page.getRecords());
        return resultMap;
    }

    /**
     * 根据教师的名称查询教师信息是否存在
     * @param coachName
     * @return
     */
    @GetMapping("coachNameExist")
    public DataResults coachNameExist(String  coachName){
        // select count(*) from coach where coachName = ? and del = 0;
        int count = coachService.count(new QueryWrapper<Coach>().eq("coachName", coachName).eq("del", 0));
        return DataResults.success(ResultCode.SUCCESS,count);
    }

    /**
     * 新增教师信息
     * @param coach
     * @return
     */
    @PostMapping("add")
    public DataResults add(Coach coach){
        coach.setDel(0);
        boolean result = coachService.save(coach);
        if(result){
            return DataResults.success(ResultCode.SUCCESS);
        }else{
            return DataResults.success(ResultCode.FAIL);
        }
    }

    /**
     * 根据主键查询对应的教师信息
     * @param coachId
     * @return
     */
    @GetMapping("queryById/{coachId}")
    public DataResults queryById(@PathVariable("coachId") Integer coachId){
        Coach coach = coachService.getById(coachId);
        return DataResults.success(ResultCode.SUCCESS,coach);
    }

    /**
     * 修改教师信息
     * @param coach
     * @return
     */
    @PutMapping("update")
    public DataResults update(Coach coach){
        boolean result = coachService.updateById(coach);
        if(result){
            return DataResults.success(ResultCode.SUCCESS);
        }else{
            return DataResults.success(ResultCode.FAIL);
        }
    }

    /**
     * 删除coach信息
     * @param coachId
     * @return
     */
    @DeleteMapping("delete/{coachId}")
    public DataResults delete(@PathVariable("coachId") Integer coachId){
        // update coach set del = 1 where coachId = ?;
        Coach coach = new Coach(coachId,1);
        boolean result = coachService.updateById(coach);
        if(result){
            return DataResults.success(ResultCode.SUCCESS);
        }else{
            return DataResults.success(ResultCode.FAIL);
        }
    }

    /**
     * 查询可用的教师信息(查询coachStatic=0的教师信息)
     * @return
     */
    @GetMapping("availableList")
    public DataResults availableList(){
        // select * from coach where coachStatic = 0 and del = 0;
        List<Coach> coachList = coachService.list(new QueryWrapper<Coach>().eq("coachStatic", 0).eq("del", 0));
        return DataResults.success(ResultCode.SUCCESS,coachList);
    }
}
