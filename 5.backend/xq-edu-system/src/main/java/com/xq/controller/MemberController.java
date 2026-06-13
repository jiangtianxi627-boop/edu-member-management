package com.xq.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xq.pojo.Member;
import com.xq.pojo.Membertype;
import com.xq.service.IMemberService;
import com.xq.service.IMembertypeService;
import com.xq.utils.DataResults;
import com.xq.utils.DateTimeUtils;
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
 * @since 2025-05-13
 */
@RestController
@Slf4j
@RequestMapping("/member")
public class MemberController {

    @Autowired
    IMemberService memberService;

    @Autowired
    IMembertypeService membertypeService;

    /**
     * 查询会员信息，包括会员对应的会员类型信息
     * @return
     */
    @GetMapping("list")
    public DataResults list(){
        List<Member> memberList = memberService.list(new QueryWrapper<Member>().eq("del", 0));
        for(Member member : memberList){
            Integer typId = member.getMemberTypes();
            Membertype membertype = membertypeService.getById(typId);
            member.setMembertype(membertype);
        }
        return DataResults.success(ResultCode.SUCCESS,memberList);
    }

    /**
     * 分页查询vip学员信息
     * @param hyname
     * @param ktype
     * @param pageSize
     * @param pageNumber
     * @return
     */
    @GetMapping("queryPage")
    public Map<String,Object> queryPage(String hyname,Integer ktype,Integer pageSize,Integer pageNumber){
        Map<String,Object> resultMap = new HashMap<>();
        Map<String,Object> paramMap = new HashMap<>();
        paramMap.put("hyname",hyname);
        paramMap.put("ktype",ktype);
        paramMap.put("pageStart",(pageNumber - 1) * pageSize);
        paramMap.put("pageSize",pageSize);

        int totalCount = memberService.totalCount(paramMap);
        List<Member> memberList = memberService.pageMembers(paramMap);
        resultMap.put("total",totalCount);
        resultMap.put("rows",memberList);
        return resultMap;
    }

    /**
     * 新增vip学员信息
     * @param member
     * @return
     */
    @PostMapping("add")
    public DataResults add(Member member){
        log.info("新增的会员数据是:" + member);
        member.setMemberbalance(0.0F);
        member.setMemberStatic(1);
        member.setDel(0);
        boolean result = memberService.save(member);
        if(result){
            return DataResults.success(ResultCode.SUCCESS);
        }else {
            return DataResults.success(ResultCode.FAIL);
        }
    }

    /**
     * 修改vip学员数据
     * @param member
     * @return
     */
    @PutMapping("update")
    public DataResults update(Member member){
        log.info("更新的数据是:" + member);
        member.setDel(0);
        boolean result = memberService.updateById(member);
        if(result){
            return DataResults.success(ResultCode.SUCCESS);
        }else {
            return DataResults.success(ResultCode.FAIL);
        }
    }

    /**
     * 根据会员id查询会员学员信息
     * @param memberId
     * @return
     */
   @GetMapping("queryById/{memberId}")
   public DataResults queryById(@PathVariable("memberId") Integer memberId){
       Member member = memberService.getById(memberId);
       Integer memberTypes = member.getMemberTypes();
       Membertype membertype = membertypeService.getById(memberTypes);
       member.setMembertype(membertype);
       return DataResults.success(ResultCode.SUCCESS,member);
   }

   @GetMapping("queryPageExpire")
   public Map<String,Object>  queryPageExpire(String hyname,Integer ktype,Integer pageSize,Integer pageNumber){
       Map<String,Object> resultMap = new HashMap<>();
       Map<String,Object> paramMap = new HashMap<>();
       paramMap.put("hyname",hyname);
       paramMap.put("ktype",ktype);
       paramMap.put("pageStart",(pageNumber - 1) * pageSize);
       paramMap.put("pageSize",pageSize);
       paramMap.put("nowtime", DateTimeUtils.nowTime());

       int totalCount = memberService.totalCount(paramMap);
       List<Member> memberList = memberService.pageMembers(paramMap);
       resultMap.put("total",totalCount);
       resultMap.put("rows",memberList);
       return resultMap;
   }

}
