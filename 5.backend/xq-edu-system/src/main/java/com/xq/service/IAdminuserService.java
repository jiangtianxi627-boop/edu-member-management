package com.xq.service;

import com.xq.pojo.Adminuser;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xq
 * @since 2025-05-24
 */
public interface IAdminuserService extends IService<Adminuser>, UserDetailsService {

    void add(Adminuser adminuser, String roleIds);

    void updateAdminUser(Adminuser adminuser, String roleIds);

}
