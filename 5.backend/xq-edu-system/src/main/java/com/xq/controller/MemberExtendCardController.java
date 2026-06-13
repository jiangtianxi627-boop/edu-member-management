package com.xq.controller;

import com.xq.pojo.Member;
import com.xq.service.IMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("memberExtendCard")
public class MemberExtendCardController {

    @Autowired
    IMemberService memberService;

    /**
     * 查询续卡记录
     * @param pageSize
     * @param pageNumber
     * @param hyname
     * @param ktype
     * @return
     */
    @GetMapping("queryPage")
    public Map<String,Object> queryPage(Integer pageSize,Integer pageNumber,String hyname,Integer ktype){
        Map<String,Object> map = new HashMap<>();
        Map<String,Object> pamramMap = new HashMap<>();
        pamramMap.put("hyname",hyname);
        pamramMap.put("ktype",ktype);
        pamramMap.put("pageStart",(pageNumber - 1) * pageSize);
        pamramMap.put("pageSize",pageSize);

        int totalCount = memberService.totalCount(pamramMap);
        List<Member> memberList = memberService.pageMembers(pamramMap);
        map.put("total",totalCount);
        map.put("rows",memberList);
        return map;
    }
}
