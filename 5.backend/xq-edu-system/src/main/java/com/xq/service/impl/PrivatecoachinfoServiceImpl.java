package com.xq.service.impl;

import com.xq.pojo.Member;
import com.xq.pojo.Privatecoachinfo;
import com.xq.mapper.PrivatecoachinfoMapper;
import com.xq.service.IMemberService;
import com.xq.service.IPrivatecoachinfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xq
 * @since 2025-05-14
 */
@Service
public class PrivatecoachinfoServiceImpl extends ServiceImpl<PrivatecoachinfoMapper, Privatecoachinfo> implements IPrivatecoachinfoService {

    @Autowired
    PrivatecoachinfoMapper privatecoachinfoMapper;

    @Autowired
    IMemberService memberService;

    //查询总记录数
    @Override
    public int totalCount(Map<String, Object> paramMap) {
        return privatecoachinfoMapper.totalCount(paramMap);
    }

    // 查询当前页面的数据
    @Override
    public List<Privatecoachinfo> pages(Map<String, Object> paramMap) {
        return privatecoachinfoMapper.pages(paramMap);
    }


    @Transactional
    @Override
    public void add(Privatecoachinfo privatecoachinfo) {
        // 1. 更新vip学员信息的余额
        Member member = memberService.getById(privatecoachinfo.getMemberid());
        member.setMemberbalance(member.getMemberbalance() - privatecoachinfo.getCountprice());
        memberService.updateById(member);
        // 2.新增学员的报课记录
        privatecoachinfoMapper.insert(privatecoachinfo);
    }
}
