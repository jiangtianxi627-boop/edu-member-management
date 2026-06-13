package com.xq.service.impl;

import com.xq.pojo.Chargerecords;
import com.xq.pojo.Member;
import com.xq.mapper.MemberMapper;
import com.xq.service.IMemberService;
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
 * @since 2025-05-13
 */
@Service
public class MemberServiceImpl extends ServiceImpl<MemberMapper, Member> implements IMemberService {

    @Autowired
    MemberMapper memberMapper;


    @Override
    public int totalCount(Map<String, Object> paramMap) {
        return memberMapper.totalCount(paramMap);
    }

    @Override
    public List<Member> pageMembers(Map<String, Object> paramMap) {
        return memberMapper.pageMembers(paramMap);
    }

}
