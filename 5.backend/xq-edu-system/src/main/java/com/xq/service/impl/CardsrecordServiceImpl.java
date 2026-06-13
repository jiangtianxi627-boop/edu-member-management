package com.xq.service.impl;

import com.xq.pojo.Cardsrecord;
import com.xq.mapper.CardsrecordMapper;
import com.xq.pojo.Member;
import com.xq.service.ICardsrecordService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xq.service.IMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xq
 * @since 2025-05-13
 */
@Service
public class CardsrecordServiceImpl extends ServiceImpl<CardsrecordMapper, Cardsrecord> implements ICardsrecordService {

    @Autowired
    IMemberService memberService;

    @Autowired
    ICardsrecordService cardsrecordService;
    @Override
    @Transactional
    public void extendCard(Cardsrecord cardsrecord) {
        // 更新会员表的到期时间和余额信息
        Member member = memberService.getById(cardsrecord.getMemberId());
        member.setMemberxufei(cardsrecord.getDaoqi());
        member.setMemberbalance(member.getMemberbalance() - cardsrecord.getMoney());
        memberService.updateById(member);
        // 新增续卡记录
        cardsrecordService.save(cardsrecord);
    }
}
