package com.xq.service;

import com.xq.pojo.Adminmenus;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xq
 * @since 2025-06-02
 */
public interface IAdminmenusService extends IService<Adminmenus> {

    List<Adminmenus> listMenusByAmindId(Integer adminId);
}
