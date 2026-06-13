package com.xq.filter;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xq.pojo.Adminmenus;
import com.xq.pojo.Adminrole;
import com.xq.service.IAdminmenusService;
import com.xq.service.IAdminroleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import java.util.Collection;
import java.util.List;

/**
 * 动态授权的过滤器
 */
@Component(value = "customerFilterInvocationSecurityMetadataSource")
public class CustomerFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

    @Autowired
    IAdminmenusService adminmenusService;

    @Autowired
    IAdminroleService adminroleService;

    private final AntPathMatcher antPathMatcher = new AntPathMatcher();

    // 动态获取某个资源需要用到哪些权限
    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        // object封装了request对象和response对象
        FilterInvocation request = (FilterInvocation)object;
        // 获取用户访问的资源地址 /index.html  /coach.html
        String url = request.getRequestUrl();
        // 进行数据库的查询操作，获取对应资源对应的角色信息
        List<Adminmenus> resources = adminmenusService.list(new QueryWrapper<Adminmenus>().eq("type", 1).eq("del", 0));
        // 遍历索引的资源，查看是否和当前的url进行匹配
        for(Adminmenus adminmenus : resources){
            if(adminmenus.getUrl()!=null && antPathMatcher.match(adminmenus.getUrl(),url)){
                // 匹配通过
                String remark = adminmenus.getRemark();// 1,2,3,8
                String[] roles = remark.split(",");
                String[] roleStr = new String[roles.length];
                for(int i = 0;i<roles.length;i++){
                    String roleId = roles[i];
                    Adminrole role = adminroleService.getById(Integer.valueOf(roleId));
                    roleStr[i] = "ROLE_" + role.getRoleName();
                }
                // 返回所有的角色信息
                return SecurityConfig.createList(roleStr);
            }
        }
        // 如果没有匹配上的，就返回一个ROLE_null的标记，此时不做任何的权限处理
        return SecurityConfig.createList("ROLE_null");
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    // 进行类型校验支持的 设置成ture
    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
