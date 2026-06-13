package com.xq.service;

import com.xq.pojo.Goodinfo;
import com.xq.pojo.Goods;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xq
 * @since 2025-05-15
 */
public interface IGoodsService extends IService<Goods> {

    void sell(Goodinfo goodinfo);
}
