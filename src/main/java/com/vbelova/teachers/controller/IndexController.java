package com.vbelova.teachers.controller;

import com.vbelova.teachers.service.EntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class IndexController {
    private final EntityService entityService;

    @RequestMapping(value = "/**", method = RequestMethod.GET)
    private String any() {
        return "redirect:/";
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    private ModelAndView cities() {
        return createCategoryView(
                "index",
                "city",
                entityService.getCities(), it -> it.id, it -> it.name
        );
    }

    @RequestMapping(value = "/city/{id}", method = RequestMethod.GET)
    private ModelAndView universities(
            @PathVariable long id
    ) {
        return createCategoryView(
                entityService.getCity(id).name,
                "university",
                entityService.getUniversities(id), it -> it.id, it -> it.name + ", " + it.address
        );
    }

    @RequestMapping(value = "/university/{id}", method = RequestMethod.GET)
    private ModelAndView faculties(
            @PathVariable long id
    ) {
        return createCategoryView(
                entityService.getUniversity(id).name,
                "faculty",
                entityService.getFaculties(id), it -> it.id, it -> it.name
        );
    }

    @RequestMapping(value = "/faculty/{id}", method = RequestMethod.GET)
    private ModelAndView cathedras(
            @PathVariable long id
    ) {
        return createCategoryView(
                entityService.getFaculty(id).name,
                "cathedra",
                entityService.getCathedras(id), it -> it.id, it -> it.name
        );
    }

    @RequestMapping(value = "/cathedra/{id}", method = RequestMethod.GET)
    private ModelAndView teachers(
            @PathVariable long id
    ) {
        return createCategoryView(
                entityService.getCathedra(id).name,
                "teacher",
                entityService.getTeachers(id), it -> it.id, it -> it.name
        );
    }

    @RequestMapping(value = "/teacher/{id}", method = RequestMethod.GET)
    private ModelAndView schedule(
            @PathVariable long id
    ) {
        return new ModelAndView("schedule")
                .addObject("title", entityService.getTeacher(id).name);
    }

    private static <T> ModelAndView createCategoryView(
            String title,
            String prefix,
            List<T> items,
            Function<T, Long> keyFunction,
            Function<T, String> valueFunction
    ) {
            return new ModelAndView("category")
                    .addObject("title", title)
                    .addObject("prefix", prefix)
                    .addObject("items", items.stream().collect(Collectors.toMap(keyFunction, valueFunction)));
        }

}


