package com.xq.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xq.pojo.Membertype;
import com.xq.service.IMembertypeService;
import com.xq.utils.DataResults;
import com.xq.utils.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
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
 * @since 2025-04-25
 */
@RestController // @RestController  =  @Controller  + @ResponseBody
@Slf4j
@RequestMapping("/membertype")
public class MembertypeController {

    @Autowired
    IMembertypeService iMembertypeService;

    // 测试Thymeleaf是否可用
   /* @RequestMapping("/testThymeleaf")
    public String testThymeleaf(Model model){
        model.addAttribute("username","eric");
        return "hello";
    }*/

    /**
     * 会员卡类型的查询
     * @return
     */
    @GetMapping("/list")
    public  DataResults list(){
        List<Membertype> list = iMembertypeService.list(new QueryWrapper<Membertype>().eq("typeDel",0));
        return DataResults.success(ResultCode.SUCCESS,list);
    }

    /**
     * 分页查询会员卡类型数据
     * @param typeName
     * @param pageNumber
     * @param pageSize
     * @return
     */
    @GetMapping("queryPage")
    public Map<String,Object> queryPage(String typeName,Integer pageNumber,Integer pageSize){
        Map<String,Object> resultMap = new HashMap<>();
        log.info("typeName:" + typeName);
        log.info("pageSize:" + pageSize);
        log.info("pageNumber:" + pageNumber);

        QueryWrapper<Membertype> q = new QueryWrapper<>();
        // like() 做模糊查询 select * from membertype where typeName like "%typeName%"
        q.like(typeName != null && !"".equals(typeName),"typeName",typeName);
        // and typeDel = 0
        q.eq("typeDel",0); // 将没有逻辑删除的记录查询出来
        // 调用mybatis-plus的分页插件
        IPage<Membertype> page = iMembertypeService.page(new Page<Membertype>(pageNumber, pageSize), q);
        resultMap.put("total",page.getTotal());
        resultMap.put("rows",page.getRecords());
        return resultMap;
    }

    /**
     * 新增会员卡类型
     * @param membertype
     * @return
     */
    @PostMapping("/add")
    public DataResults add(Membertype membertype){
        log.info("新增的数据是:" + membertype);
        membertype.setTypeDel(0);
        boolean result = iMembertypeService.save(membertype);
        if(result){
            return DataResults.success(ResultCode.SUCCESS);
        }else{
            return DataResults.success(ResultCode.FAIL);
        }
    }

    /**
     * 根据会员卡类型id回显会员卡信息
     * @param typeId
     * @return
     */
    @GetMapping("queryById/{typeId}")
    public DataResults queryById(@PathVariable("typeId") Integer typeId){
        Membertype membertype = iMembertypeService.getById(typeId);
        return DataResults.success(ResultCode.SUCCESS,membertype);
    }

    /**
     * 修改会员卡类型数据
     * @param membertype
     * @return
     */
    @PutMapping("update")
    public DataResults update(Membertype membertype){
        log.info("更新之后的数据是:" + membertype);
        boolean result = iMembertypeService.updateById(membertype);
        if(result){
            return DataResults.success(ResultCode.SUCCESS);
        }else{
            return DataResults.success(ResultCode.FAIL);
        }
    }

    /**
     * 逻辑删除会员卡类型数据
     * @param typeId
     * @return
     */
    @DeleteMapping("delete/{typeId}")
    public DataResults delete(@PathVariable("typeId") Integer typeId){
        Membertype membertype = new Membertype(typeId, 1);
        boolean result = iMembertypeService.updateById(membertype);
        if(result){
            return DataResults.success(ResultCode.SUCCESS);
        }else{
            return DataResults.success(ResultCode.FAIL);
        }

    }

    /**
     *  根据会员卡类型查询会员天数
     * @param typeId
     * @return
     */
    @GetMapping("getDays/{typeId}")
    public DataResults getDays(@PathVariable("typeId") Integer typeId){
        Membertype membertype = iMembertypeService.getById(typeId);
        return DataResults.success(ResultCode.SUCCESS,membertype);
    }

}
// http://localhost:8083/membertype/list