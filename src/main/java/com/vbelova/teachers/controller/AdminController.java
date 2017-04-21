package com.vbelova.teachers.controller;

import com.vbelova.teachers.service.EntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import static com.vbelova.teachers.controller.IndexController.*;

@RestController
@RequiredArgsConstructor
@SuppressWarnings("unused")
@RequestMapping(value = "/admin")
public class AdminController {

    private final EntityService entityService;

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    private void delete(@RequestBody DeleteRequest request) {
        switch (request.category) {
            case CATEGORY_CITY:
                entityService.deleteCity(request.id); break;
            case CATEGORY_UNIVERSITY:
                entityService.deleteUniversity(request.id); break;
            case CATEGORY_FACULTY:
                entityService.deleteFaculty(request.id); break;
            case CATEGORY_CATHEDRA:
                entityService.deleteCathedra(request.id); break;
            case CATEGORY_TEACHER:
                entityService.deleteTeacher(request.id); break;
            default: throw new IllegalArgumentException("Unknown category: " + request.category);
        }
    }

    private static class DeleteRequest {
        public String category;
        public long id;
    }

}
