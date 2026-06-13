package com.xq.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xq.pojo.Adminuser;
import com.xq.service.IAdminuserService;
import com.xq.utils.DataResults;
import com.xq.utils.DateTimeUtils;
import com.xq.utils.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author xq
 * @since 2025-05-24
 */
@RestController
@Slf4j
@RequestMapping("/adminuser")
public class AdminuserController {

    @Autowired
    IAdminuserService adminuserService;

    /**
     * 修改密码
     * @param oldPassword
     * @param newPassword
     * @param newPasswordAgain
     * @param session
     * @return
     */
    @PostMapping("updatePwd")
    public DataResults updatePwd(String oldPassword, String newPassword, String newPasswordAgain, HttpSession session){
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        // 从session中获取用户信息
        Adminuser loginUser = (Adminuser) session.getAttribute("loginUser");
        // 比对用户输入的旧密码是否正确
        boolean matches = bCryptPasswordEncoder.matches(oldPassword, loginUser.getAdminPassword());
        if(matches){ // 旧密码输入正确
            // 比较用户输入的新密码和确认密码是否一致
            if(newPassword.equals(newPasswordAgain)){
                if(!oldPassword.equals(newPassword)){ // 修改的密码不能和原来旧的密码相同
                    loginUser.setAdminPassword(bCryptPasswordEncoder.encode(newPassword));
                    adminuserService.updateById(loginUser);
                    // session要失效
                    session.invalidate();
                    return DataResults.success(ResultCode.SUCCESS);
                }else{ // 新旧密码一样
                    return DataResults.success(ResultCode.SAME_PASSWORD);
                }
            }else{
                // 新旧密码不一致
                return DataResults.success(ResultCode.PASSWORD_ERROR);
            }
        }else{ // 旧密码输入错误
            return DataResults.success(ResultCode.PASSWORD_ERROR);
        }

    }

    /**
     * 分页查询用户数据
     * @param pageSize
     * @param pageNumber
     * @param adminName
     * @return
     */
    @GetMapping("queryPage")
    public Map<String,Object> queryPage(Integer pageSize, Integer pageNumber, String adminName){
        Map<String,Object> resultMap = new HashMap<>();
        QueryWrapper<Adminuser> q = new QueryWrapper<>();
        q.like(StringUtils.isNotEmpty(adminName),"adminName",adminName);
        q.eq("del",0);
        // 调用mybatis-plus分页插件进行分页
        IPage<Adminuser> page = adminuserService.page(new Page<Adminuser>(pageNumber, pageSize), q);
        resultMap.put("total",page.getTotal());
        resultMap.put("rows",page.getRecords());
        return resultMap;
    }

    /**
     * 添加用户信息
     * @param adminuser
     * @param roleIds
     * @return
     */
    @PostMapping("add")
    public DataResults add(Adminuser adminuser,String roleIds){ // 1,2,3
        try {
            log.info("角色id是:" + roleIds);
            adminuser.setCreateTime(DateTimeUtils.nowTime());
            adminuser.setDel(0);
            // 添加用户信息
            adminuserService.add(adminuser,roleIds);
            return DataResults.success(ResultCode.SUCCESS);
        } catch (Exception e) {
            return DataResults.success(ResultCode.FAIL);
        }
    }

    /**
     * 根据用户id查询对应的用户信息
     * @param adminId
     * @return
     */
    @GetMapping("queryById/{adminId}")
    public DataResults queryById(@PathVariable("adminId") Integer adminId){
        Adminuser adminuser = adminuserService.getById(adminId);
        return DataResults.success(ResultCode.SUCCESS,adminuser);
    }

    /**
     * 修改用户信息
     * @param adminuser
     * @param roleIds
     * @return
     */
    @PutMapping("update")
    public DataResults update(Adminuser adminuser,String roleIds){
        try {
            adminuserService.updateAdminUser(adminuser,roleIds);
            return DataResults.success(ResultCode.SUCCESS);
        } catch (Exception e) {
            return DataResults.success(ResultCode.FAIL);
        }
    }

    /**
     * 删除用户信息
     * @param adminId
     * @return
     */
    @DeleteMapping("delete/{adminId}")
    public DataResults delete(@PathVariable("adminId") Integer adminId){
        Adminuser adminuser = new Adminuser(adminId,1);
        boolean b = adminuserService.updateById(adminuser);
        if(b){
            return DataResults.success(ResultCode.SUCCESS);
        }else{
            return DataResults.success(ResultCode.FAIL);
        }
    }

}
