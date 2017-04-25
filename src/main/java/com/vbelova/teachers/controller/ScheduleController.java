package com.vbelova.teachers.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor
@SuppressWarnings("unused")
public class ScheduleController {

    @GetMapping(value = "/schedule/{id}")
    private ModelAndView teachers(
            @PathVariable long id
    ) {
        return new ModelAndView("schedule");
    }
}