package com.xq.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xq.pojo.Goods;
import com.xq.service.IGoodsService;
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
 * @since 2025-05-15
 */
@RestController
@Slf4j
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    IGoodsService goodsService;

    /**
     * 查询所有的商品列表
     * @return
     */
    @GetMapping("list")
    public DataResults list(){
        List<Goods> goodsList = goodsService.list(new QueryWrapper<Goods>().eq("del", 0));
        return DataResults.success(ResultCode.SUCCESS,goodsList);
    }

    /**
     * 分页查询商品信息
     * @param pageNumber
     * @param pageSize
     * @param goodsName
     * @return
     */
    @GetMapping("queryPage")
    public Map<String,Object> queryPage(Integer pageNumber,Integer pageSize,String goodsName){
        Map<String,Object> resultMap = new HashMap<>();
        // 组装查询条件  select * from goods where goodName like ? and del = 0;
        QueryWrapper<Goods> q = new QueryWrapper<>();
        q.like(StringUtils.isNotEmpty(goodsName),"goodsName",goodsName);
        q.eq("del",0);
        // 调用分页插件完成分页的查询操作
        IPage<Goods> page = goodsService.page(new Page<Goods>(pageNumber, pageSize), q);
        resultMap.put("total",page.getTotal());
        resultMap.put("rows",page.getRecords());
        return resultMap;
    }

    /**
     * 根据商品名称判断对应的商品记录是否存在
     * @param goodsName
     * @return
     */
    @GetMapping("goodsNameExist")
    public DataResults goodsNameExist(String goodsName){
        // select count(*) from goods where goodsName = ? and del = 0;
        int count = goodsService.count(new QueryWrapper<Goods>().eq("goodsName", goodsName).eq("del", 0));
        return DataResults.success(ResultCode.SUCCESS,count);
    }

    /**
     * 新增商品信息
     * @param goods
     * @return
     */
    @PostMapping("add")
    public DataResults add(Goods goods){
        log.info("新增的商品信息是:" + goods);
        goods.setDel(0);
        boolean result = goodsService.save(goods);
        if(result){
            return DataResults.success(ResultCode.SUCCESS);
        }else{
            return DataResults.success(ResultCode.FAIL);
        }
    }

    /**
     * 根据商品id查询对应的商品信息
     * @param id
     * @return
     */
    @GetMapping("queryById/{id}")
    public DataResults queryById(@PathVariable("id") Integer id){
        Goods goods = goodsService.getById(id);
        return DataResults.success(ResultCode.SUCCESS,goods);
    }

    /**
     * 商品进货 退货的业务实现
     * @param goods
     * @return
     */
    @PutMapping("update")
    public DataResults update(Goods goods){
        log.info("进货或者退货之后的商品信息:" + goods);
        boolean result = goodsService.updateById(goods);
        if(result){
            return DataResults.success(ResultCode.SUCCESS);
        }else{
            return DataResults.success(ResultCode.FAIL);
        }
    }

    /**
     * 删除商品信息
     * @param goodsId
     * @return
     */
    @DeleteMapping("delete/{goodsId}")
    public DataResults delete(@PathVariable("goodsId") Integer goodsId){
        Goods goods = new Goods(goodsId,1);
        boolean result = goodsService.updateById(goods);
        if(result){
            return DataResults.success(ResultCode.SUCCESS);
        }else{
            return DataResults.success(ResultCode.FAIL);
        }
    }


}
