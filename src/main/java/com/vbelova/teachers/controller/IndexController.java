package com.vbelova.teachers.controller;

import com.vbelova.teachers.entity.*;
import com.vbelova.teachers.service.EntityService;
import com.vbelova.teachers.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
@SuppressWarnings("unused")
public class IndexController {

    static final String CATEGORY_CITY = "city";
    static final String CATEGORY_UNIVERSITY = "university";
    static final String CATEGORY_FACULTY = "faculty";
    static final String CATEGORY_CATHEDRA = "cathedra";
    static final String CATEGORY_TEACHER = "teacher";

    private final EntityService entityService;
    private final UserService userService;

    @RequestMapping(value = "/*", method = RequestMethod.GET)
    private String any() {
        return "redirect:/";
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    private ModelAndView cities() {
        return createCategoryView(
                "index",
                CATEGORY_CITY,
                entityService.getCities(), it -> it.id, it -> it.name
        );
    }

    @RequestMapping(value =  "/" + CATEGORY_CITY + "/{id}", method = RequestMethod.GET)
    private ModelAndView universities(
            @PathVariable long id
    ) {
        return createCategoryView(
                entityService.get(City.class, id).name,
                CATEGORY_UNIVERSITY,
                entityService.getUniversities(id), it -> it.id, it -> it.name + ", " + it.address
        );
    }

    @RequestMapping(value = "/" + CATEGORY_UNIVERSITY + "/{id}", method = RequestMethod.GET)
    private ModelAndView faculties(
            @PathVariable long id
    ) {
        return createCategoryView(
                entityService.get(University.class, id).name,
                CATEGORY_FACULTY,
                entityService.getFaculties(id), it -> it.id, it -> it.name
        );
    }

    @RequestMapping(value = "/" + CATEGORY_FACULTY + "/{id}", method = RequestMethod.GET)
    private ModelAndView cathedras(
            @PathVariable long id
    ) {
        return createCategoryView(
                entityService.get(Faculty.class, id).name,
                CATEGORY_CATHEDRA,
                entityService.getCathedras(id), it -> it.id, it -> it.name
        );
    }

    @RequestMapping(value = "/" + CATEGORY_CATHEDRA + "/{id}", method = RequestMethod.GET)
    private ModelAndView teachers(
            @PathVariable long id
    ) {
        return createCategoryView(
                entityService.get(Cathedra.class, id).name,
                CATEGORY_TEACHER,
                entityService.getTeachers(id), it -> it.id, it -> it.name
        );
    }

    @RequestMapping(value = "/"+ CATEGORY_TEACHER +"/{id}", method = RequestMethod.GET)
    private ModelAndView schedule(
            @PathVariable long id
    ) {
        return new ModelAndView("schedule")
                .addObject("title", entityService.get(Teacher.class, id).name);
    }

    private <T> ModelAndView createCategoryView(
            String title,
            String prefix,
            List<T> items,
            Function<T, Long> keyFunction,
            Function<T, String> valueFunction
    ) {
        Map<Long, String> itemsResult = items.stream()
                .sorted(Comparator.comparing(keyFunction))
                .collect(Collectors.toMap(keyFunction, valueFunction));
        return new ModelAndView("category")
                    .addObject("title", title)
                    .addObject("prefix", prefix)
                    .addObject("items", itemsResult)
                    .addObject("isAdmin", userService.isAdmin());
    }

}


