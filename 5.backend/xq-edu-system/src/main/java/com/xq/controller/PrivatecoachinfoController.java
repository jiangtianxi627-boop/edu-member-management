package com.xq.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xq.pojo.Coach;
import com.xq.pojo.Member;
import com.xq.pojo.Privatecoachinfo;
import com.xq.pojo.Subject;
import com.xq.service.ICoachService;
import com.xq.service.IMemberService;
import com.xq.service.IPrivatecoachinfoService;
import com.xq.service.ISubjectService;
import com.xq.utils.DataResults;
import com.xq.utils.ResultCode;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.util.ArrayList;
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
@Slf4j
@RequestMapping("/privatecoachinfo")
public class PrivatecoachinfoController {

    @Autowired
    IPrivatecoachinfoService privatecoachinfoService;

    /**
     * 查询vip学员课程 教师详情信息
     * @param coachName
     * @param subname
     * @param pageNumber
     * @param pageSize
     * @param memberid
     * @return
     */
    @GetMapping("queryPage")
    public Map<String,Object> queryPage(String coachName,String subname,Integer pageNumber,Integer pageSize,Integer memberid){
        Map<String,Object> resultMap = new HashMap<>();
        // 将查询条件封装到一个Map集合中
        Map<String,Object> paramMap = new HashMap<>();
        paramMap.put("memberid",memberid);
        paramMap.put("coachName",coachName);
        paramMap.put("subname",subname);
        paramMap.put("pageStart",(pageNumber - 1) * pageSize);
        paramMap.put("pageSize",pageSize);

        // 调用业务接口查询total总记录数、rows当前页面的数据
        int totalCount = privatecoachinfoService.totalCount(paramMap);
        List<Privatecoachinfo> memberList = privatecoachinfoService.pages(paramMap);
        resultMap.put("total",totalCount);
        resultMap.put("rows",memberList);
        return resultMap;
    }

    @Autowired
    ISubjectService subjectService;

    @Autowired
    IMemberService memberService;

    @Autowired
    ICoachService coachService;

    /**
     * 根据vip学员id查询课程的详情信息
     * @param id
     * @return
     */
    @GetMapping("getDetailsById/{id}")
    public List<Privatecoachinfo> getDetailsById(@PathVariable("id") Integer id){
        Privatecoachinfo privatecoachinfo = privatecoachinfoService.getById(id);

        Integer subjectid = privatecoachinfo.getSubjectid();
        Subject subject = subjectService.getById(subjectid);
        privatecoachinfo.setSubject(subject);

        Integer memberid = privatecoachinfo.getMemberid();
        Member member = memberService.getById(memberid);
        privatecoachinfo.setMember(member);

        Integer coachid = privatecoachinfo.getCoachid();
        Coach coach = coachService.getById(coachid);
        privatecoachinfo.setCoach(coach);

        List<Privatecoachinfo> list = new ArrayList<>();
        list.add(privatecoachinfo);
        return list;
    }

    /**
     * 查询vip学员的课程次数
     * @param memid
     * @return
     */
    @GetMapping("count")
    public DataResults count(Integer memid){
        int count = privatecoachinfoService.count(new QueryWrapper<Privatecoachinfo>().eq("del", 0).eq("memberid", memid));
        return DataResults.success(ResultCode.SUCCESS,count);
    }

    /**
     * 学员课程新增
     * @param privatecoachinfo
     * @return
     */
    @PostMapping("add")
    public DataResults add(Privatecoachinfo privatecoachinfo){
        privatecoachinfo.setDel(0);
        privatecoachinfo.setAdmin("匿名");
        log.info("学员新增课程详情是:" + privatecoachinfo);
        privatecoachinfoService.add(privatecoachinfo);
        return DataResults.success(ResultCode.SUCCESS);
    }

    /**
     * 变更vip学员教师
     * @param privatecoachinfo
     * @return
     */
    @PutMapping("update")
    public DataResults update(Privatecoachinfo privatecoachinfo){
        log.info("变更之后的报课信息是:" + privatecoachinfo);
        privatecoachinfoService.updateById(privatecoachinfo);
        return DataResults.success(ResultCode.SUCCESS);
    }

    /**
     * 逻辑删除vip学员报课信息
     * @param id
     * @return
     */
    @DeleteMapping("delete/{id}")
    public DataResults delete(@PathVariable("id") Integer id){
        Privatecoachinfo privatecoachinfo = new Privatecoachinfo(id, 1);
        privatecoachinfoService.updateById(privatecoachinfo);
        return DataResults.success(ResultCode.SUCCESS);
    }

}
