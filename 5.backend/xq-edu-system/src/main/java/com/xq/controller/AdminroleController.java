package com.xq.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xq.pojo.AdminUserRole;
import com.xq.pojo.Adminrole;
import com.xq.service.IAdminUserRoleService;
import com.xq.service.IAdminroleService;
import com.xq.utils.DataResults;
import com.xq.utils.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author xq
 * @since 2025-05-25
 */
@RestController
@Slf4j
@RequestMapping("/adminrole")
public class AdminroleController {

    @Autowired
    IAdminroleService adminroleService;

    @Autowired
    IAdminUserRoleService adminUserRoleService;

    // 查询所有的角色信息
    @GetMapping("/list")
    public DataResults list(){
        List<Adminrole> adminroleList = adminroleService.list(new QueryWrapper<Adminrole>().eq("del", 0));
        return DataResults.success(ResultCode.SUCCESS,adminroleList);
    }

    // 分页查询角色信息
    @GetMapping("/queryPage")
    public Map<String,Object> queryPage(Integer pageSize,Integer pageNumber,String roleName){
        Map<String,Object> resultMap = new HashMap<>();
        // 组装查询条件
        QueryWrapper<Adminrole> q = new QueryWrapper<>();
        q.like(StringUtils.isNotEmpty(roleName),"roleName",roleName);
        q.eq("del",0);
        // 调用分页插件
        IPage<Adminrole> page = adminroleService.page(new Page<Adminrole>(pageNumber, pageSize), q);
        resultMap.put("total",page.getTotal());
        resultMap.put("rows",page.getRecords());
        return resultMap;
    }

    // 新增角色信息
    @PostMapping("add")
    public DataResults add(Adminrole adminrole,String menusIds){
        try {
            adminrole.setDel("0");
            // 新增角色
            adminroleService.add(adminrole,menusIds);
            return DataResults.success(ResultCode.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return DataResults.success(ResultCode.FAIL);
        }
    }

    //根据角色id回显角色信息
    @GetMapping("queryById/{id}")
    public DataResults queryById(@PathVariable("id") Integer id){
        Adminrole adminrole = adminroleService.getById(id);
        return DataResults.success(ResultCode.SUCCESS,adminrole);
    }

    // 修改角色信息
    @PutMapping("update")
    public DataResults update(Adminrole adminrole,String menusIds){
        try {
            // 更新角色
            adminroleService.updateRole(adminrole,menusIds);
            return DataResults.success(ResultCode.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return DataResults.success(ResultCode.FAIL);
        }
    }

    // 删除角色信息
    @DeleteMapping("delete/{id}")
    public DataResults delete(@PathVariable("id") Integer id){
        // 判断是否存在需要删除的角色信息  SELECT COUNT(*) FROM `admin_user_role` WHERE `roleId` = ?
        int count = adminUserRoleService.count(new QueryWrapper<AdminUserRole>().eq("roleId",id));
        if(count > 0){ // 当前角色和用户存在关联关系，不允许删除
            return DataResults.success(ResultCode.NO_DELETE);
        }else{
            // 进行删除操作
            Adminrole adminrole = new Adminrole(id,"1");
            boolean b = adminroleService.deleRole(adminrole);
            if(b){
                return DataResults.success(ResultCode.SUCCESS);
            }else{
                return DataResults.success(ResultCode.FAIL);
            }
        }
    }

}
