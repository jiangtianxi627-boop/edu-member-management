package com.xq.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xq.pojo.Chargerecords;
import com.xq.pojo.Member;
import com.xq.pojo.Membertype;
import com.xq.service.IChargerecordsService;
import com.xq.service.IMemberService;
import com.xq.service.IMembertypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author xq
 * @since 2025-05-13
 */
@RestController
@RequestMapping("/chargerecords")
public class ChargerecordsController {

    @Autowired
    IChargerecordsService chargerecordsService;

    @Autowired
    IMemberService memberService;

    @Autowired
    IMembertypeService membertypeService;

    @GetMapping("queryPage")
    public Map<String,Object> queryPage(Integer pageNumber,Integer pageSize,String xdate,String ddate){
        Map<String,Object> resultMap = new HashMap<>();

        QueryWrapper<Chargerecords> q = new QueryWrapper<>();
        q.ge(StringUtils.isNotEmpty(xdate),"chargetime",xdate);
        q.le(StringUtils.isNotEmpty(xdate),"chargetime",ddate);
        q.eq("del",0);// 查询没有删除的记录
        IPage<Chargerecords> page = chargerecordsService.page(new Page<Chargerecords>(pageNumber, pageSize), q);
        List<Chargerecords> chargerecordsList = page.getRecords();
        for(Chargerecords chargerecords : chargerecordsList){
            //会员id
            Integer memberId = chargerecords.getMemberId();
            // 查询会员对象
            Member member = memberService.getById(memberId);
            // 会员类型id
            Integer memberTypes = member.getMemberTypes();
            // 查询会员类型对象
            Membertype membertype = membertypeService.getById(memberTypes);
            member.setMembertype(membertype);
            chargerecords.setMember(member);
        }
        resultMap.put("total",page.getTotal());
        resultMap.put("rows",chargerecordsList);
        return resultMap;
    }

}
