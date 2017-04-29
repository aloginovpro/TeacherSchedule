package com.vbelova.teachers.service;

import com.google.common.collect.ImmutableList;

import java.util.List;
import java.util.Map;

public interface ScheduleService {

    List<String> PAIR_INTERVALS = ImmutableList.of(
            "08:30-10:00",
            "10:10-11:40",
            "11:50-13:20",
            "13:35-15:05",
            "15:20-16:50",
            "17:00-18:30",
            "18:40-20:10"
    );

    List<String> WEEK_DAYS = ImmutableList.of(
            "Mon",
            "Tue",
            "Wed",
            "Thu",
            "Fri",
            "Sat",
            "Sun"
    );

    Map<Long, String> getSubjects(long teacherId);

    String[][] getSchedule(long teacherId);

    void updateSubjects(long teacherId, List<String> subjects);

    void updateSchedule(long teacherId, List<List<String>> table);

}
