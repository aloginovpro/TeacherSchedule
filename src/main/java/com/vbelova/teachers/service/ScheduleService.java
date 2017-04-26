package com.vbelova.teachers.service;

import java.util.List;
import java.util.Map;

public interface ScheduleService {

    Map<Long, String> getSubjects(long teacherId);

    String[][] getSchedule(long teacherId);

    void updateSubjects(long teacherId, List<String> subjects);

    void updateSchedule(long teacherId, List<List<String>> table);

}
