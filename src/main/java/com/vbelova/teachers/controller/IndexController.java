package com.vbelova.teachers.controller;

import com.google.common.collect.ImmutableMap;
import com.vbelova.teachers.entity.*;
import com.vbelova.teachers.service.EntityService;
import com.vbelova.teachers.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Controller
@SuppressWarnings("unused")
public class IndexController {

    static final String CATEGORY_CITY = "city";
    static final String CATEGORY_UNIVERSITY = "university";
    static final String CATEGORY_FACULTY = "faculty";
    static final String CATEGORY_CATHEDRA = "cathedra";
    static final String CATEGORY_TEACHER = "teacher";

    private final EntityService entityService;
    private final UserService userService;

    public IndexController(EntityService entityService, UserService userService) {
        this.entityService = entityService;
        this.userService = userService;
    }

    @GetMapping(value = "/*")
    private String any() {
        return "redirect:/";
    }

    @GetMapping(value = "/")
    private ModelAndView cities() {
        return createCategoryView(
                null,
                CATEGORY_CITY,
                0,
                entityService.getCities(), it -> it.id, it -> it.name,
                City.class
        );
    }

    @GetMapping(value =  "/" + CATEGORY_CITY + "/{id}")
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

    @GetMapping(value = "/" + CATEGORY_UNIVERSITY + "/{id}")
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

    @GetMapping(value = "/" + CATEGORY_FACULTY + "/{id}")
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

    @GetMapping(value = "/" + CATEGORY_CATHEDRA + "/{id}")
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

    @GetMapping(value = "/"+ CATEGORY_TEACHER +"/{id}")
    private ModelAndView schedule(
            @PathVariable long id
    ) {
        return createCategoryView(
                entityService.get(Teacher.class, id),
                null,
                id,
                null, null, null,
                null
        );
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
        Map<Long, String> itemsResult = items == null ? null : items.stream()
                .sorted(Comparator.comparing(keyFunction))
                .collect(Collectors.toMap(keyFunction, valueFunction));
        return new ModelAndView("category")
                    .addObject("title", entity == null ? "Title" : entity.getName())
                    .addObject("description", getFieldNameToValueMap(entity))
                    .addObject("prefix", prefix)
                    .addObject("categoryId", categoryId)
                    .addObject("items", itemsResult)
                    .addObject("htmlInput", getFieldNameToTypeMap(categoryClass))
                    .addObject("isAdmin", userService.isAdmin())
                    .addObject("isAuthorized", userService.isAuthorized())
                    .addObject("isCategoryTeacher", entity != null && entity.getClass() == Teacher.class);
    }

    private static Map<String, String> getFieldNameToTypeMap(Class clazz) {
        if (clazz == null) {
            return null;
        }
        return Arrays.stream(clazz.getDeclaredFields())
                .filter(IndexController::filterField)
                .collect(Collectors.toMap(Field::getName, it -> javaClassToHtmlInputType(it.getType())));
    }

    private static Map<String, Object> getFieldNameToValueMap(Object o) {
        if (o == null) {
            return null;
        }
        return Arrays.stream(o.getClass().getDeclaredFields())
                .filter(IndexController::filterField)
                .collect(Collectors.toMap(Field::getName, it -> getFieldValue(it, o)));

    }

    private static boolean filterField(Field field) {
        return !field.getName().equals("id") && !field.getName().endsWith("Id");
    }

    private static Object getFieldValue(Field f, Object o) {
        try {
            return f.get(o);
        } catch (IllegalAccessException e) {
            return null;
        }
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


