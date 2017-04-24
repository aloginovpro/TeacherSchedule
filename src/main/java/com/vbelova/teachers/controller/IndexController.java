package com.vbelova.teachers.controller;

import com.google.common.collect.ImmutableMap;
import com.vbelova.teachers.entity.*;
import com.vbelova.teachers.service.EntityService;
import com.vbelova.teachers.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.lang.reflect.Field;
import java.util.Arrays;
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
                null,
                CATEGORY_CITY,
                0,
                entityService.getCities(), it -> it.id, it -> it.name,
                City.class
        );
    }

    @RequestMapping(value =  "/" + CATEGORY_CITY + "/{id}", method = RequestMethod.GET)
    private ModelAndView universities(
            @PathVariable long id
    ) {
        return createCategoryView(
                entityService.get(City.class, id),
                CATEGORY_UNIVERSITY,
                id,
                entityService.getUniversities(id), it -> it.id, it -> it.name,
                University.class
        );
    }

    @RequestMapping(value = "/" + CATEGORY_UNIVERSITY + "/{id}", method = RequestMethod.GET)
    private ModelAndView faculties(
            @PathVariable long id
    ) {
        return createCategoryView(
                entityService.get(University.class, id),
                CATEGORY_FACULTY,
                id,
                entityService.getFaculties(id), it -> it.id, it -> it.name,
                Faculty.class
        );
    }

    @RequestMapping(value = "/" + CATEGORY_FACULTY + "/{id}", method = RequestMethod.GET)
    private ModelAndView cathedras(
            @PathVariable long id
    ) {
        return createCategoryView(
                entityService.get(Faculty.class, id),
                CATEGORY_CATHEDRA,
                id,
                entityService.getCathedras(id), it -> it.id, it -> it.name,
                Cathedra.class
        );
    }

    @RequestMapping(value = "/" + CATEGORY_CATHEDRA + "/{id}", method = RequestMethod.GET)
    private ModelAndView teachers(
            @PathVariable long id
    ) {
        return createCategoryView(
                entityService.get(Cathedra.class, id),
                CATEGORY_TEACHER,
                id,
                entityService.getTeachers(id), it -> it.id, it -> it.name,
                Teacher.class
        );
    }

    @RequestMapping(value = "/"+ CATEGORY_TEACHER +"/{id}", method = RequestMethod.GET)
    private ModelAndView schedule(
            @PathVariable long id
    ) {
        Teacher entity = entityService.get(Teacher.class, id);
        return new ModelAndView("schedule")
                .addObject("title", entity.getName())
                .addObject("description", entity.toString().replace("\n", "<br>"))
                .addObject("isAdmin", userService.isAdmin());
    }

    private <T> ModelAndView createCategoryView(
            CategoryEntity entity,
            String prefix,
            long categoryId,
            List<T> items,
            Function<T, Long> keyFunction,
            Function<T, String> valueFunction,
            Class categoryClass
    ) {
        Map<Long, String> itemsResult = items.stream()
                .sorted(Comparator.comparing(keyFunction))
                .collect(Collectors.toMap(keyFunction, valueFunction));
        return new ModelAndView("category")
                    .addObject("title", entity == null ? "Title" : entity.getName())
                    .addObject("description", entity == null ? null : entity.toString().replace("\n", "<br>"))
                    .addObject("prefix", prefix)
                    .addObject("categoryId", categoryId)
                    .addObject("items", itemsResult)
                    .addObject("htmlInput", getFieldNameToTypeMap(categoryClass))
                    .addObject("isAdmin", userService.isAdmin());
    }

    private static Map<String, String> getFieldNameToTypeMap(Class clazz) {
        return Arrays.stream(clazz.getDeclaredFields())
                .filter(it -> !it.getName().equals("id") && !it.getName().endsWith("Id"))
                .collect(Collectors.toMap(Field::getName, it -> javaClassToHtmlInputType(it.getType())));
    }

    private static final Map<Class, String> javaClassToHtmlInputType = ImmutableMap.of(
            String.class, "text",
            Long.class, "number",
            Integer.class, "number"
    );
    private static String javaClassToHtmlInputType(Class clazz) {
        String result = javaClassToHtmlInputType.get(clazz);
        if (result == null) {
            throw new IllegalArgumentException("Class " + clazz + "not supported");
        }
        return result;
    }

}


