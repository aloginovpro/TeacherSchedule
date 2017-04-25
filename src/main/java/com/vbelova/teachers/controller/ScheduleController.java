package com.vbelova.teachers.controller;

import com.vbelova.teachers.entity.Teacher;
import com.vbelova.teachers.service.EntityService;
import com.vbelova.teachers.service.ScheduleService;
import com.vbelova.teachers.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collection;

@Controller
@RequiredArgsConstructor
@SuppressWarnings("unused")
public class ScheduleController {

    private final ScheduleService scheduleService;
    private final UserService userService;
    private final EntityService entityService;

    @GetMapping(value = "/schedule/{teacherId}")
    private ModelAndView teachers(
            @PathVariable long teacherId
    ) {
        Collection<String> subjects = scheduleService.getSubjects(teacherId).values();
        String[][] schedule = scheduleService.getSchedule(teacherId);
        return new ModelAndView("schedule")
                .addObject("teacherName", entityService.get(Teacher.class, teacherId).name)
                .addObject("isAdmin", userService.isAdmin())
                .addObject("subjects", subjects)
                .addObject("schedule", schedule)
                .addObject("id", teacherId);
    }

}