package com.xq.controller;

import com.xq.pojo.Coach;
import com.xq.service.ICoachService;
import com.xq.service.IMemberService;
import com.xq.service.ISubjectService;
import com.xq.service.StatisticsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("statistics")
@Slf4j
public class StatisticsController {

    @Autowired
    StatisticsService statisticsService;

    @Autowired
    ICoachService coachService;
    @Autowired
    IMemberService memberService;
    @Autowired
    ISubjectService subjectService;

    @GetMapping("tongji")
    public int[] tongji(String year){
        log.info("查询的年份是:" + year); // 2025
        String[] array = {year + "-01",year + "-02",year + "-03",year + "-04",
                year + "-05",year + "-06",year + "-07",year + "-08",
                year + "-09",year + "-10",year + "-11",year + "-12"};
        int[] arr = new int[12];
        for(int i = 0;i<arr.length;i++){
            arr[i] = statisticsService.sumMoneyByDate(array[i]);
        }
        return arr;
    }

    @GetMapping("coachKpi")
    public Map<String,Object> coachKpi(String year){
        Map<String,Object> map = new HashMap<>();
        List<Coach> list = coachService.list();
        for(Coach coach : list){
            int kpi = statisticsService.sumKpiByCoach(year,coach.getCoachId());
            map.put(coach.getCoachName(),kpi);
        }
        return map;
    }

    @GetMapping("counts")
    public Map<String,Integer> counts(){
        Map<String,Integer> map = new HashMap<>();
        map.put("memberCount",memberService.count());
        map.put("coachCount",coachService.count());
        map.put("subjectCount",subjectService.count());
        return map;
    }
}
