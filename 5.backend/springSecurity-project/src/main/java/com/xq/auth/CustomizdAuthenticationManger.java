package com.xq.auth;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 自定义认证管理器
 */
public class CustomizdAuthenticationManger implements UserDetailsService {

    // 定义一个Map集合，模拟数据库里面保存的用户信息
    public Map<String, com.xq.pojo.User> map  = new HashMap<String,com.xq.pojo.User>();

    public void  initUsers(){
        com.xq.pojo.User user1 = new com.xq.pojo.User();
        user1.setUsername("admin");
        user1.setPassword("admin");
        com.xq.pojo.User user2 = new com.xq.pojo.User();
        user2.setUsername("xiaoming");
        user2.setPassword("1234");

        map.put(user1.getUsername(),user1);
        map.put(user2.getUsername(),user2);
    }

    // 自定义认证逻辑
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        initUsers();
        System.out.println("页面上获取的用户信息是:" + username);
        com.xq.pojo.User userInDb = map.get(username); // 模拟从数据库中根据用户名查询用户信息
        if(userInDb == null){
            return null;
        }
        String passwordInDb = "{noop}" + userInDb.getPassword();// 模拟从数据库取出密码
        // 给用户进行角色的授予
        List<GrantedAuthority> list = new ArrayList<GrantedAuthority>();
        list.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        UserDetails user = new User(username,passwordInDb,list);
        return user;
    }
}
