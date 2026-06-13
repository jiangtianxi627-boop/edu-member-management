package com.xq.service;

import com.xq.pojo.Cardsrecord;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xq
 * @since 2025-05-13
 */
public interface ICardsrecordService extends IService<Cardsrecord> {

    void extendCard(Cardsrecord cardsrecord);
}
