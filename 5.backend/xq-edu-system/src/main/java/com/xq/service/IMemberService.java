package com.xq.service;

import com.xq.pojo.Chargerecords;
import com.xq.pojo.Member;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xq
 * @since 2025-05-13
 */
public interface IMemberService extends IService<Member> {

    int totalCount(Map<String, Object> paramMap);

    List<Member> pageMembers(Map<String, Object> paramMap);

}
