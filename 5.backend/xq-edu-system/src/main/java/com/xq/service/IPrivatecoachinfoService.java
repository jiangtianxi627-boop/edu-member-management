package com.xq.service;

import com.xq.pojo.Privatecoachinfo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xq
 * @since 2025-05-14
 */
public interface IPrivatecoachinfoService extends IService<Privatecoachinfo> {

    int totalCount(Map<String, Object> paramMap);

    List<Privatecoachinfo> pages(Map<String, Object> paramMap);

    void add(Privatecoachinfo privatecoachinfo);
}
