package com.xq.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xq.pojo.Subject;
import com.xq.service.ISubjectService;
import com.xq.utils.DataResults;
import com.xq.utils.ResultCode;
import lombok.extern.slf4j.Slf4j;
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
 * @since 2025-05-12
 */
@RestController
@Slf4j
@RequestMapping("/subject")
public class SubjectController {

    @Autowired
    ISubjectService subjectService;

    @RequestMapping("list")
    public DataResults list(){
        List<Subject> subjectList = subjectService.list(new QueryWrapper<Subject>().eq("del", 0));
        return DataResults.success(ResultCode.SUCCESS,subjectList);
    }

    /**
     * 课程信息的分页查询
     * @param subname
     * @param pageSize
     * @param pageNumber
     * @return
     */
    @GetMapping("queryPage")
    public Map<String,Object> queryPage(String subname,Integer pageSize,Integer pageNumber){
        Map<String,Object> map = new HashMap<>();
        log.info("科目名称:" + subname);
        log.info("当前页码:" + pageNumber);
        log.info("当前页面容量:" + pageSize);
        QueryWrapper<Subject> q = new QueryWrapper<>();
        q.like(subname != null && !"".equals(subname),"subname",subname);
        q.eq("del",0);
        // 调用mybatis-plus分页插件
        IPage<Subject> page = subjectService.page(new Page<Subject>(pageNumber, pageSize), q);
        map.put("total",page.getTotal());
        map.put("rows",page.getRecords());
        return map;
    }

    /**
     * 根据课程名称查询课程信息是否存在
     * @param subname
     * @return
     */
    @GetMapping("subnameExist")
    public DataResults subNameExists(String subname){
        // select count(*) from subject where subname = ? and del = ?
        int count = subjectService.count(new QueryWrapper<Subject>().eq("subname", subname).eq("del", 0));
        return DataResults.success(ResultCode.SUCCESS,count);
    }

    /**
     * 新增课程信息
     * @param subject
     * @return
     */
    @PostMapping("add")
    public DataResults add(Subject subject){
        log.info("新增的数据是:" + subject);
        subject.setDel(0);
        boolean result = subjectService.save(subject);
        if(result){
            return DataResults.success(ResultCode.SUCCESS);
        }else{
            return DataResults.success(ResultCode.FAIL);
        }
    }

    /**
     * 根据课程id查询对应的课程信息
     * @param subId
     * @return
     */
    @GetMapping("queryById/{subId}")
    public DataResults queryById(@PathVariable("subId") Integer subId){
        Subject subject = subjectService.getById(subId);
        return DataResults.success(ResultCode.SUCCESS,subject);
    }


    /**
     * 更新数据
     * @param subject
     * @return
     */
    @PutMapping("update")
    public DataResults update(Subject subject){
        log.info("更新之后的数据是:" + subject);
        boolean result = subjectService.updateById(subject);
        if(result){
            return DataResults.success(ResultCode.SUCCESS);
        }else{
            return DataResults.success(ResultCode.FAIL);
        }
    }

    /**
     * 删除课程信息
     * @param subId
     * @return
     */
    @DeleteMapping("delete/{subId}")
    public DataResults delete(@PathVariable("subId") Integer subId){
        log.info("删除课程的id是:" + subId);
        Subject subject = new Subject(subId, 1);
        boolean result = subjectService.updateById(subject);
        if(result){
            return DataResults.success(ResultCode.SUCCESS);
        }else{
            return DataResults.success(ResultCode.FAIL);
        }
    }

}
