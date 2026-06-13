package com.xq.service.impl;

import com.xq.pojo.Adminmenus;
import com.xq.mapper.AdminmenusMapper;
import com.xq.service.IAdminmenusService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xq
 * @since 2025-06-02
 */
@Service
public class AdminmenusServiceImpl extends ServiceImpl<AdminmenusMapper, Adminmenus> implements IAdminmenusService {

    @Autowired
    AdminmenusMapper adminmenusMapper;

    @Override
    public List<Adminmenus> listMenusByAmindId(Integer adminId) {
        return adminmenusMapper.listMenusByAmindId(adminId);
    }
}
