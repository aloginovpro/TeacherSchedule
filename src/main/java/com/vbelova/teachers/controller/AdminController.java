package com.vbelova.teachers.controller;

import com.vbelova.teachers.entity.Teacher;
import com.vbelova.teachers.service.EntityService;
import com.vbelova.teachers.service.ScheduleService;
import com.vbelova.teachers.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static org.apache.commons.lang3.StringUtils.join;

@Controller
@SuppressWarnings("unused")
public class AdminController {

    private final ScheduleService scheduleService;
    private final UserService userService;
    private final EntityService entityService;

    public AdminController(ScheduleService scheduleService, UserService userService, EntityService entityService) {
        this.scheduleService = scheduleService;
        this.userService = userService;
        this.entityService = entityService;
    }

    @GetMapping(value = "/admin/subjects/{teacherId}")
    private ModelAndView subjects(
            @PathVariable long teacherId
    ) {
        Collection<String> subjects = scheduleService.getSubjects(teacherId).values()
                .stream().sorted().collect(Collectors.toList());
        return new ModelAndView("subjects")
                .addObject("teacherName", entityService.get(Teacher.class, teacherId).name)
                .addObject("isAdmin", userService.isAdmin())
                .addObject("subjects", join(subjects, "\n"))
                .addObject("id", teacherId);
    }

    @PostMapping(value = "/admin/subjects/{teacherId}")
    private String updateSubjects(
            @PathVariable long teacherId,
            @RequestParam String subjects
    ) {
        List<String> subjectsList = Arrays.stream(subjects.split("\n"))
                .map(String::trim)
                .filter(it -> !it.isEmpty())
                .collect(Collectors.toList());
        scheduleService.updateSubjects(teacherId, subjectsList);
        return "redirect:/schedule/" + teacherId;
    }

}