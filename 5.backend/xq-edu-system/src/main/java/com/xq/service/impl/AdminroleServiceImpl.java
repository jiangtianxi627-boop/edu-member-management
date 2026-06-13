package com.xq.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xq.pojo.AdminRoleMenus;
import com.xq.pojo.Adminrole;
import com.xq.mapper.AdminroleMapper;
import com.xq.service.IAdminRoleMenusService;
import com.xq.service.IAdminmenusService;
import com.xq.service.IAdminroleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xq
 * @since 2025-05-25
 */
@Service
public class AdminroleServiceImpl extends ServiceImpl<AdminroleMapper, Adminrole> implements IAdminroleService {

    @Autowired
    AdminroleMapper adminroleMapper;

    @Autowired
    IAdminRoleMenusService adminRoleMenusService;

    @Override
    @Transactional
    public void add(Adminrole adminrole, String menusIds) {
        // 新增角色表本身的信息
        adminroleMapper.insert(adminrole);
        String[] menuIdsArray = menusIds.split(",");
        List<AdminRoleMenus> adminRoleMenusList = new ArrayList<>();
        for(int i = 0;i<menuIdsArray.length;i++){
            AdminRoleMenus adminRoleMenus = new AdminRoleMenus(null,adminrole.getId(),Integer.valueOf(menuIdsArray[i]));
            adminRoleMenusList.add(adminRoleMenus);
        }
        adminRoleMenusService.saveBatch(adminRoleMenusList);
    }

    @Override
    @Transactional
    public void updateRole(Adminrole adminrole, String menusIds) {
        // 先修改角色表本身的信息
        adminroleMapper.updateById(adminrole);
        // 清除中间表的原来的关联关系
        adminRoleMenusService.remove(new QueryWrapper<AdminRoleMenus>().eq("roleId",adminrole.getId()));
        // 新增中间表
        String[] menuIdsArray = menusIds.split(",");
        List<AdminRoleMenus> adminRoleMenusList = new ArrayList<>();
        for(int i = 0;i<menuIdsArray.length;i++){
            AdminRoleMenus adminRoleMenus = new AdminRoleMenus(null,adminrole.getId(),Integer.valueOf(menuIdsArray[i]));
            adminRoleMenusList.add(adminRoleMenus);
        }
        adminRoleMenusService.saveBatch(adminRoleMenusList);
    }

    @Override
    @Transactional
    public boolean deleRole(Adminrole adminrole) {
        // 逻辑删除角色表的数据
        int i = adminroleMapper.updateById(adminrole);
        // 删除中间表的关联关系
        boolean b = adminRoleMenusService.remove(new QueryWrapper<AdminRoleMenus>().eq("roleId", adminrole.getId()));
        return i > 0 && b;
    }
}
