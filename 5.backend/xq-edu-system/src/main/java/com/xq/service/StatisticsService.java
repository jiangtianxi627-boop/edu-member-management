package com.xq.service;

public interface StatisticsService {
    int sumMoneyByDate(String date);

    int sumKpiByCoach(String year, Integer coachId);
}
