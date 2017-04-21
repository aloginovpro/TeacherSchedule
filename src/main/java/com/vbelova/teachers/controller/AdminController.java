package com.vbelova.teachers.controller;

import com.google.common.collect.ImmutableMap;
import com.vbelova.teachers.entity.*;
import com.vbelova.teachers.service.EntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

import static com.vbelova.teachers.controller.IndexController.*;

@RestController
@RequiredArgsConstructor
@SuppressWarnings("unused")
@RequestMapping(value = "/admin")
public class AdminController {

    private static final Map<String, Class<?>> categoryNameToClassMap = ImmutableMap.of(
            CATEGORY_CITY, City.class,
            CATEGORY_UNIVERSITY, University.class,
            CATEGORY_FACULTY, Faculty.class,
            CATEGORY_CATHEDRA, Cathedra.class,
            CATEGORY_TEACHER, Teacher.class
    );

    private final EntityService entityService;

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    private void delete(@RequestBody DeleteRequest request) {
        entityService.delete(categoryNameToClassMap.get(request.category), request.id);
    }

    private static class DeleteRequest {
        public String category;
        public long id;
    }

}
