package com.vbelova.teachers.service;

import com.vbelova.teachers.entity.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EntityService {

    List<City> getCities();
    List<University> getUniversities(long cityId);
    List<Faculty> getFaculties(long universityId);
    List<Cathedra> getCathedras(long facultyId);
    List<Teacher> getTeachers(long cathedraId);

    <T> void save(Class<T> clazz, T t);

    void delete(Class<?> clazz, long id);

    <T> T get(Class<T> clazz, long id);

}
