package com.vbelova.teachers.controller;

import com.google.common.collect.ImmutableMap;
import com.vbelova.teachers.entity.*;
import com.vbelova.teachers.service.EntityService;
import com.vbelova.teachers.service.ScheduleService;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

import static com.vbelova.teachers.controller.IndexController.*;

@RestController
@SuppressWarnings("unused")
@RequestMapping(value = "/admin")
public class AdminRestController {

    private static final Map<String, Class<?>> categoryNameToClassMap = ImmutableMap.of(
            CATEGORY_CITY, City.class,
            CATEGORY_UNIVERSITY, University.class,
            CATEGORY_FACULTY, Faculty.class,
            CATEGORY_CATHEDRA, Cathedra.class,
            CATEGORY_TEACHER, Teacher.class
    );

    private final EntityService entityService;
    private final ScheduleService scheduleService;

    public AdminRestController(EntityService entityService, ScheduleService scheduleService) {
        this.entityService = entityService;
        this.scheduleService = scheduleService;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    private BaseResponse delete(@RequestBody DeleteRequest request) {
        entityService.delete(categoryNameToClassMap.get(request.category), request.id);
        return new BaseResponse(null);
    }

    private static class DeleteRequest {
        public String category;
        public long id;
    }

    private static class BaseResponse {
        public final String error;

        private BaseResponse(String error) {
            this.error = error;
        }
    }

    @PostMapping("/add/" + CATEGORY_CITY)
    private BaseResponse add(
            @Valid @RequestBody City entity,
            BindingResult bindingResult
    ) {
        return add(City.class, entity, bindingResult);
    }

    @PostMapping("/add/" + CATEGORY_UNIVERSITY)
    private BaseResponse add(
            @RequestParam long to,
            @Valid @RequestBody University entity,
            BindingResult bindingResult
    ) {
        entity.cityId = to;
        return add(University.class, entity, bindingResult);
    }

    @PostMapping("/add/" + CATEGORY_FACULTY)
    private BaseResponse add(
            @RequestParam long to,
            @Valid @RequestBody Faculty entity,
            BindingResult bindingResult
    ) {
        entity.universityId = to;
        return add(Faculty.class, entity, bindingResult);
    }

    @PostMapping("/add/" + CATEGORY_CATHEDRA)
    private BaseResponse add(
            @RequestParam long to,
            @Valid @RequestBody Cathedra entity,
            BindingResult bindingResult
    ) {
        entity.facultyId = to;
        return add(Cathedra.class, entity, bindingResult);
    }

    @PostMapping("/add/" + CATEGORY_TEACHER)
    private BaseResponse add(
            @RequestParam long to,
            @Valid @RequestBody Teacher entity,
            BindingResult bindingResult
    ) {
        entity.cathedraId = to;
        return add(Teacher.class, entity, bindingResult);
    }

    private <T> BaseResponse add(Class<T> clazz, T entity, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            entityService.save(clazz, entity);
            return new BaseResponse(null);
        }
        StringBuilder sb = new StringBuilder();
        bindingResult.getAllErrors().forEach(error -> sb.append(error.getDefaultMessage()).append("\n"));
        return new BaseResponse(sb.toString());
    }

    @PostMapping(value = "/updateSchedule/{teacherId}")
    private BaseResponse updateSchedule(
            @PathVariable long teacherId,
            @RequestBody UpdateScheduleRequest req
    ) {
        scheduleService.updateSchedule(teacherId, req.table);
        return new BaseResponse(null);
    }

    public static class UpdateScheduleRequest {
        public List<List<String>> table;
    }

}
