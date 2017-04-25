package com.vbelova.teachers.service;

import com.vbelova.teachers.entity.ScheduleItem;
import com.vbelova.teachers.repository.ScheduleItemRepository;
import com.vbelova.teachers.repository.SubjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final SubjectRepository subjectRepository;
    private final ScheduleItemRepository scheduleItemRepository;

    public Map<Long, String> getSubjects(long teacherId) {
        return subjectRepository.findByTeacherId(teacherId).stream()
                .collect(Collectors.toMap(it -> it.id, it -> it.name));
    }

    public String[][] getSchedule(long teacherId) {
        Map<Long, String> subjects = getSubjects(teacherId);
        String[][] result = new String[24][7];
        List<ScheduleItem> scheduleItems = scheduleItemRepository.findByTeacherId(teacherId);
        scheduleItems.forEach(
                item -> result[item.hour][item.day] = subjects.get(item.subjectId)
        );
        return result;
    }

}
