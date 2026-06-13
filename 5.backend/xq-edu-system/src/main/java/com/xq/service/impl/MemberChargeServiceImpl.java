package com.xq.service.impl;

import com.xq.pojo.Chargerecords;
import com.xq.pojo.Member;
import com.xq.service.IChargerecordsService;
import com.xq.service.IMemberChargeService;
import com.xq.service.IMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author kriss
 * @version 1.0.0
 * @date 2025-05-13 21:46
 * @description TODO
 */
@Service
public class MemberChargeServiceImpl implements IMemberChargeService {

    @Autowired
    IMemberService memberService;

    @Autowired
    IChargerecordsService chargerecordsService;

    @Override
    @Transactional
    public void charge(Chargerecords chargerecords) {
        // 更新vip学员的余额信息
        Member member = memberService.getById(chargerecords.getMemberId());
        member.setMemberbalance(member.getMemberbalance() + chargerecords.getMoney());
        memberService.updateById(member);
        // 新增充值记录
        chargerecordsService.save(chargerecords);
    }
}
