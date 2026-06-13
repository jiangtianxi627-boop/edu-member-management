package com.xq.mapper;

import org.apache.ibatis.annotations.Param;

public interface StatisticsMapper {
    int sumMoneyByDate(@Param("date") String date);

    int sumKpiByCoach(@Param("year") String year, @Param("coachId") Integer coachId);
}
