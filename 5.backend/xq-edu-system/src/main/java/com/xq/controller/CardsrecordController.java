package com.xq.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xq.pojo.Cardsrecord;
import com.xq.pojo.Member;
import com.xq.pojo.Membertype;
import com.xq.service.ICardsrecordService;
import com.xq.service.IMemberService;
import com.xq.service.IMembertypeService;
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
 * <p>
 *  前端控制器
 * </p>
 *
 * @author xq
 * @since 2025-05-13
 */
@RestController
@Slf4j
@RequestMapping("/cardsrecord")
public class CardsrecordController {

    @Autowired
    ICardsrecordService cardsrecordService;

    @Autowired
    IMemberService memberService;

    @Autowired
    IMembertypeService membertypeService;

    /**
     * 续卡业务
     * @param cardsrecord
     * @return
     */
    @PostMapping("extendCard")
    public DataResults extendCard(Cardsrecord cardsrecord){
       try {
           cardsrecord.setDel(0);
           cardsrecord.setCreatetime(DateTimeUtils.nowTime());
           log.info("续卡数据是:" + cardsrecord);
           // 续卡业务
           cardsrecordService.extendCard(cardsrecord);
           return DataResults.success(ResultCode.SUCCESS);
       }catch (Exception ex){
           ex.printStackTrace();
           return DataResults.success(ResultCode.FAIL);
       }
    }

    /**
     * 续卡记录分页查询
     * @return
     */
    @GetMapping("queryPage")
    public Map<String,Object> queryPage(Integer pageSize,Integer pageNumber,String xdate,String ddate){
        Map<String,Object> resultMap = new HashMap<>();
        QueryWrapper<Cardsrecord> q = new QueryWrapper<>();
        q.ge(StringUtils.isNotEmpty(xdate),"createtime",xdate);
        q.le(StringUtils.isNotEmpty(ddate),"createtime",ddate);
        q.eq("del",0);
        IPage<Cardsrecord> page = cardsrecordService.page(new Page<Cardsrecord>(pageNumber, pageSize), q);
        List<Cardsrecord> cardsrecordList = page.getRecords();
        for(Cardsrecord cardsrecord : cardsrecordList){
            Integer memberId = cardsrecord.getMemberId();
            // 查询会员对象
            Member member = memberService.getById(memberId);
            // 会员卡id
            Integer memberTypes = cardsrecord.getTypeId();
            // 查询会员卡类型对象
            Membertype membertype = membertypeService.getById(memberTypes);
            member.setMembertype(membertype);
            cardsrecord.setMember(member);
        }
        resultMap.put("total",page.getTotal());
        resultMap.put("rows",cardsrecordList);
        return  resultMap;
    }
}
