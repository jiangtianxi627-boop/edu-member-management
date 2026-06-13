package com.xq.mapper;

import com.xq.pojo.Privatecoachinfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author xq
 * @since 2025-05-14
 */
public interface PrivatecoachinfoMapper extends BaseMapper<Privatecoachinfo> {

    int totalCount(Map<String, Object> paramMap);

    List<Privatecoachinfo> pages(Map<String, Object> paramMap);
}
