package com.xq.handler;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.gson.Gson;
import com.xq.pojo.AdminUserRole;
import com.xq.pojo.Adminmenus;
import com.xq.pojo.Adminrole;
import com.xq.pojo.Adminuser;
import com.xq.service.IAdminUserRoleService;
import com.xq.service.IAdminmenusService;
import com.xq.service.IAdminroleService;
import com.xq.service.IAdminuserService;
import com.xq.utils.DataResults;
import com.xq.utils.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *  异步认证成功的处理器
 */
@Component(value = "authenticationSuccessHandler")
@Slf4j
public class AuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    @Autowired
    IAdminuserService adminuserService;

    @Autowired
    IAdminUserRoleService adminUserRoleService;

    @Autowired
    IAdminroleService adminroleService;

    @Autowired
    IAdminmenusService adminmenusService;
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        // 设置后端向前端响应的数据的json格式
        response.setContentType("application/json;charset=utf-8");
        // 获取认证成功的用户信息(从springSecurity的上下文对象中获取的，loadUserByUsername封装了用户对象之后，会将该对象放在springSecurity上下文对象中)
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        log.info("登录成功的用用户是:" + userDetails.getUsername());
        // 存储用户认证成功的用户对象
        Adminuser loginUser = adminuserService.getOne(new QueryWrapper<Adminuser>().eq("adminName", userDetails.getUsername()));
        request.getSession().setAttribute("loginUser",loginUser);

        // 查询登录用户的角色信息
        Integer adminId = loginUser.getAdminId();
        List<AdminUserRole> userRoles = adminUserRoleService.list(new QueryWrapper<AdminUserRole>().eq("adminId", adminId));
        // 封装角色的集合
        List<Adminrole> adminroleList = new ArrayList<>();
        boolean isAdmin = false;
        for(AdminUserRole userRole : userRoles){
            Integer roleId = userRole.getRoleId();
            Adminrole adminrole = adminroleService.getById(roleId);
            adminroleList.add(adminrole);
            if(adminrole.getRoleName().equals("管理员")){
                isAdmin = true;
            }
        }
        request.getSession().setAttribute("adminroleList",adminroleList);
        request.getSession().setAttribute("isAdmin",isAdmin);
        // 查询登录成功的用户的菜单权限资源（根据用户id查询对应的菜单信息）
        List<Adminmenus> adminmenusList = adminmenusService.listMenusByAmindId(adminId);
        request.getSession().setAttribute("adminmenusList",adminmenusList);
        // 向前端响应数据
        DataResults<String> results = DataResults.success(ResultCode.SUCCESS);
        response.getWriter().write(new Gson().toJson(results));
    }
}
