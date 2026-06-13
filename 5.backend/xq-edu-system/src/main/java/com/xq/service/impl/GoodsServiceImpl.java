package com.xq.service.impl;

import com.xq.mapper.GoodinfoMapper;
import com.xq.pojo.Goodinfo;
import com.xq.pojo.Goods;
import com.xq.mapper.GoodsMapper;
import com.xq.pojo.Member;
import com.xq.service.IGoodsService;
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
 * @since 2025-05-15
 */
@Service
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements IGoodsService {

    @Autowired
    IGoodsService goodsService;

    @Autowired
    GoodinfoMapper goodinfoMapper;

    @Autowired
    IMemberService memberService;

    @Override
    @Transactional
    public void sell(Goodinfo goodinfo) {
        // 1. 扣库存
        Goods goods = goodsService.getById(goodinfo.getGoodsid());
        goods.setInventory(goods.getInventory() - goodinfo.getCount());
        goodsService.updateById(goods);
        // 2. 减去会员的余额
        Member member = memberService.getById(goodinfo.getMemberid());
        member.setMemberbalance(member.getMemberbalance() - goodinfo.getPrice());
        memberService.updateById(member);
        // 3.新增消费记录
        goodinfoMapper.insert(goodinfo);
    }
}
