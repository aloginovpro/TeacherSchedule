package com.vbelova.teachers.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice(basePackages = "com.vbelova.teachers.controller")
public class ExceptionController {
    private final Logger log = LoggerFactory.getLogger(ExceptionController.class);

    @ExceptionHandler(Exception.class)
    private ModelAndView onException(Exception e) {
        log.error("Error happened", e);
        return new ModelAndView("error");
    }

}
