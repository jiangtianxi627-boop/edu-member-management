package com.xq.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xq.pojo.Equipment;
import com.xq.service.IEquipmentService;
import com.xq.utils.DataResults;
import com.xq.utils.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author xq
 * @since 2025-05-12
 */
@RestController
@Slf4j
@RequestMapping("/equipment")
public class EquipmentController {

    @Autowired
    IEquipmentService equipmentService;

    @GetMapping("/list")
    public DataResults list() {
        List<Equipment> list = equipmentService.list(new QueryWrapper<Equipment>().eq("del", 0));
        return DataResults.success(ResultCode.SUCCESS, list);
    }

    /**
     * 分页查询教学器材数据
     *
     * @param eqName
     * @param pageNumber
     * @param pageSize
     * @return
     */
    @GetMapping("queryPage")
    public Map<String, Object> queryPage(String eqName, Integer pageNumber, Integer pageSize) {
        Map<String, Object> map = new HashMap<>();
        QueryWrapper<Equipment> q = new QueryWrapper<>();
        q.like(eqName != null && !"".equals(eqName), "eqName", eqName);
        q.eq("del", 0); // 默认查询del=0的数据，逻辑删除的数据不查询
        // 使用mybatis-plus插件事项分页查询操作
        IPage<Equipment> page = equipmentService.page(new Page<Equipment>(pageNumber, pageSize), q);
        map.put("total", page.getTotal());
        map.put("rows", page.getRecords());
        return map;
    }

    /**
     * 添加器材数据
     *
     * @param equipment
     * @return
     */
    @PostMapping("add")
    public DataResults add(Equipment equipment) {
        equipment.setDel(0);
        boolean result = equipmentService.save(equipment);
        if (result) {
            return DataResults.success(ResultCode.SUCCESS);
        } else {
            return DataResults.fail(ResultCode.FAIL);
        }
    }

    /**
     * 删除器材信息
     * @param eqId
     * @return
     */
    @DeleteMapping("delete/{eqId}")
    public DataResults delete(@PathVariable("eqId") Integer eqId) {
        Equipment equipment = new Equipment(eqId, 1);
        boolean result = equipmentService.updateById(equipment);
        if (result) {
            return DataResults.success(ResultCode.SUCCESS);
        } else {
            return DataResults.fail(ResultCode.FAIL);
        }
    }
}