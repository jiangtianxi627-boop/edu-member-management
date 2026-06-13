package com.xq.service.impl;

import com.xq.mapper.StatisticsMapper;
import com.xq.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author kriss
 * @version 1.0.0
 * @date 2025-05-17 23:43
 * @description TODO
 */
@Service
public class StatisticsServiceImpl implements StatisticsService {

    @Autowired
    StatisticsMapper statisticsMapper;
    @Override
    public int sumMoneyByDate(String date) {
        return statisticsMapper.sumMoneyByDate(date);
    }

    @Override
    public int sumKpiByCoach(String year, Integer coachId) {
        return statisticsMapper.sumKpiByCoach(year,coachId);
    }
}
