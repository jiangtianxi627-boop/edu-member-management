package com.xq.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xq.pojo.Goodinfo;
import com.xq.pojo.Goods;
import com.xq.pojo.Member;
import com.xq.service.IGoodinfoService;
import com.xq.service.IGoodsService;
import com.xq.service.IMemberService;
import com.xq.utils.DataResults;
import com.xq.utils.DateTimeUtils;
import com.xq.utils.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import javax.annotation.security.RolesAllowed;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author xq
 * @since 2025-05-15
 */
@RestController
@Slf4j
@RequestMapping("/goodinfo")
public class GoodinfoController {

    @Autowired
    IGoodinfoService goodinfoService;

    @Autowired
    IMemberService memberService;

    @Autowired
    IGoodsService goodsService;

    @GetMapping("list")
    public DataResults list(){
        List<Goodinfo> goodinfoList = goodinfoService.list(new QueryWrapper<Goodinfo>().eq("del", 0));
        return DataResults.success(ResultCode.SUCCESS,goodinfoList);
    }

    /**
     * 分页查询商品销售信息
     * @param pageSize
     * @param pageNumber
     * @param goodsid
     * @param memberid
     * @return
     */
    @GetMapping("queryPage")
    public Map<String,Object> queryPage(Integer pageSize,Integer pageNumber,Integer goodsid,Integer memberid){
        Map<String,Object> resultMap = new HashMap<>();
        // 组装查询条件
        QueryWrapper<Goodinfo> q = new QueryWrapper<>();
        q.eq(goodsid != null,"goodsid",goodsid);
        q.eq(memberid != null,"memberid",memberid);
        q.eq("del",0);

        // 调用mybatis-plus的分页插件进行查询
        IPage<Goodinfo> page = goodinfoService.page(new Page<Goodinfo>(pageNumber, pageSize), q);
        List<Goodinfo> records = page.getRecords();
        for(Goodinfo goodinfo : records){
            Member member = memberService.getById(goodinfo.getMemberid());
            Goods goods = goodsService.getById(goodinfo.getGoodsid());
            goodinfo.setMember(member);
            goodinfo.setGoods(goods);
        }
        resultMap.put("total",page.getTotal());
        resultMap.put("rows",records);
        return resultMap;
    }

    /**
     * 新增商品消费记录
     * @param goodinfo
     * @return
     */
    @PostMapping("add")
    public DataResults add(Goodinfo goodinfo){
        goodinfo.setDel(0);
        goodinfo.setCreatetime(DateTimeUtils.nowTime());
        // 编写消费的业务代码
        goodsService.sell(goodinfo);
        return DataResults.success(ResultCode.SUCCESS);
    }

    /**
     * 删除指定的消费记录数据
     * @param id
     * @return
     */
    //@RolesAllowed(value = {"ROLE_管理员","ROLE_教师"})
    //@Secured(value = {"ROLE_管理员","ROLE_教师"})
    //@PreAuthorize(value = "hasAnyRole('ROLE_管理员','ROLE_教师')")
    @DeleteMapping("delete/{id}")
    public DataResults delete(@PathVariable("id") Integer id){
        Goodinfo goodinfo = new Goodinfo(id,1);
        // update goodsinfo set del = 1 where id = ?;
        boolean result = goodinfoService.updateById(goodinfo);
        if(result){
            return DataResults.success(ResultCode.SUCCESS);
        }else{
            return DataResults.success(ResultCode.FAIL);
        }
    }

    /**
     * 批量删除消费记录
     * @param array
     * @return
     */
    @DeleteMapping("dellist")
    public DataResults dellist(String array){
        log.info("传递的批量数据是:" + array); // 9,12,14,15
        String[] ids = array.split(",");
        List<Goodinfo> list = new ArrayList<>();
        for(String id : ids){
            list.add(new Goodinfo(Integer.valueOf(id),1));
        }
        goodinfoService.updateBatchById(list);
        return DataResults.success(ResultCode.SUCCESS);
    }

}
