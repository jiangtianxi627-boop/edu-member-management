package com.xq.controller;

import com.xq.pojo.Chargerecords;
import com.xq.pojo.Member;
import com.xq.service.IMemberChargeService;
import com.xq.service.IMemberService;
import com.xq.utils.DataResults;
import com.xq.utils.DateTimeUtils;
import com.xq.utils.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author kriss
 * @version 1.0.0
 * @date 2025-05-13 12:52
 * @description TODO
 */
@RestController
@Slf4j
@RequestMapping("member-charge")
public class MemberChargeController {

    @Autowired
    IMemberService memberService;

    @Autowired
    IMemberChargeService memberChargeService;

    /**
     * 查询vip学员的续费信息
     * @param hyname
     * @param ktype
     * @param pageNumber
     * @param pageSize
     * @return
     */
    @GetMapping("queryPageCharge")
    public Map<String,Object> queryPageCharge(String hyname,Integer ktype,Integer pageNumber,Integer pageSize){
        Map<String, Object> resultMap = new HashMap<>();
        // 准备一个map集合封装前端传递的多个参数
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
     * 实现充值业务: 保存充值记录 + 修改vip学员的余额信息
     * @param chargerecords
     * @return
     */
    @PostMapping("charge")
    public DataResults charge(Chargerecords chargerecords){
        try {
            chargerecords.setChargetime(DateTimeUtils.nowTime());
            chargerecords.setDel(0);
            log.info("充值数据是:" + chargerecords);
            memberChargeService.charge(chargerecords);
            return DataResults.success(ResultCode.SUCCESS);
        }catch (Exception e){
            return DataResults.success(ResultCode.FAIL);
        }
    }
}
