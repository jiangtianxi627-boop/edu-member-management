package com.xq.mapper;

import com.xq.pojo.Member;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author xq
 * @since 2025-05-13
 */
public interface MemberMapper extends BaseMapper<Member> {

    int totalCount(Map<String, Object> paramMap);

    List<Member> pageMembers(Map<String, Object> paramMap);
}
