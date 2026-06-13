package com.xq.service;

import com.xq.pojo.Adminrole;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xq
 * @since 2025-05-25
 */
public interface IAdminroleService extends IService<Adminrole> {

    void add(Adminrole adminrole, String menusIds);

    void updateRole(Adminrole adminrole, String menusIds);

    boolean deleRole(Adminrole adminrole);
}
