package com.xq.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xq.pojo.AdminUserRole;
import com.xq.pojo.Adminrole;
import com.xq.pojo.Adminuser;
import com.xq.mapper.AdminuserMapper;
import com.xq.service.IAdminUserRoleService;
import com.xq.service.IAdminroleService;
import com.xq.service.IAdminuserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
 * @since 2025-05-24
 */
@Service(value = "adminuserService")
@Slf4j
public class AdminuserServiceImpl extends ServiceImpl<AdminuserMapper, Adminuser> implements IAdminuserService {


    @Autowired
    AdminuserMapper adminuserMapper;

    @Autowired
    IAdminUserRoleService adminUserRoleService;

    @Autowired
    IAdminroleService adminroleService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("页面上输入的用户名是:" + username);
        // select * from adminuser where adminName = ?;
        Adminuser adminuser = adminuserMapper.selectOne(new QueryWrapper<Adminuser>().eq("adminName", username));
        if(adminuser == null){
            throw new UsernameNotFoundException("用户信息不存在");
        }else{
            // 动态获取当前登录的用户的角色信息
            List<SimpleGrantedAuthority> authorities = new ArrayList<>();
            // 获取用户编号
            Integer adminId = adminuser.getAdminId();
            List<AdminUserRole> userRoles = adminUserRoleService.list(new QueryWrapper<AdminUserRole>().eq("adminId", adminId));
            for(AdminUserRole userRole : userRoles){
                Adminrole adminrole = adminroleService.getById(userRole.getRoleId());
                String roleName = adminrole.getRoleName();
                // 动态设置用户的角色信息
                authorities.add(new SimpleGrantedAuthority("ROLE_" + roleName));
            }
            return new User(adminuser.getAdminName(), adminuser.getAdminPassword(),authorities);
        }
    }

    @Override
    @Transactional
    public void add(Adminuser adminuser, String roleIds) {
        // 给用户密码加密
        String encode = new BCryptPasswordEncoder().encode(adminuser.getAdminPassword());
        adminuser.setAdminPassword(encode);
        adminuserMapper.insert(adminuser);
        log.info("新增用户的主键id是:" + adminuser.getAdminId());
        // 新增中间表
        String[] roleIdsArray = roleIds.split(",");
        List<AdminUserRole> adminUserRoleList = new ArrayList<>();
        for(String roleId : roleIdsArray){
            AdminUserRole adminUserRole = new AdminUserRole(null,adminuser.getAdminId(),Integer.valueOf(roleId));
            adminUserRoleList.add(adminUserRole);
        }
        adminUserRoleService.saveBatch(adminUserRoleList);
    }

    @Override
    @Transactional
    public void updateAdminUser(Adminuser adminuser, String roleIds) {
        // 更新用户表
        adminuserMapper.updateById(adminuser);
        //删除中间表中原来的关联关系
        adminUserRoleService.remove(new QueryWrapper<AdminUserRole>().eq("adminId",adminuser.getAdminId()));
        // 新增新的关联关系
        String[] roleIdsArray = roleIds.split(",");
        List<AdminUserRole> adminUserRoleList = new ArrayList<>();
        for(String roleId : roleIdsArray){
            AdminUserRole adminUserRole = new AdminUserRole(null,adminuser.getAdminId(),Integer.valueOf(roleId));
            adminUserRoleList.add(adminUserRole);
        }
        adminUserRoleService.saveBatch(adminUserRoleList);
    }
}
