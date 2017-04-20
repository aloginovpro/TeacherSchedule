package com.vbelova.teachers.service;

import com.google.common.collect.Lists;
import com.vbelova.teachers.entity.*;
import com.vbelova.teachers.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EntityService {
    private final CityRepository cityRepository;
    private final UniversityRepository universityRepository;
    private final FacultyRepository facultyRepository;
    private final CathedraRepository cathedraRepository;
    private final TeacherRepository teacherRepository;

    public List<City> getCities() {
        return Lists.newArrayList(cityRepository.findAll());
    }

    public List<University> getUniversities(long cityId) {
        return universityRepository.findByCityId(cityId);
    }

    public List<Faculty> getFaculties(long universityId) {
        return facultyRepository.findByUniversityId(universityId);
    }

    public List<Cathedra> getCathedras(long facultyId) {
        return cathedraRepository.findByFacultyId(facultyId);
    }

    public List<Teacher> getTeachers(long cathedraId) {
        return teacherRepository.findByCathedraId(cathedraId);
    }


    public City getCity(long id) {
        return checkNonNull(cityRepository.findOne(id), "City not found");
    }

    public University getUniversity(long id) {
        return checkNonNull(universityRepository.findOne(id), "University not found");
    }

    public Faculty getFaculty(long id) {
        return checkNonNull(facultyRepository.findOne(id), "Faculty not found");
    }

    public Cathedra getCathedra(long id) {
        return checkNonNull(cathedraRepository.findOne(id), "Cathedra not found");
    }

    public Teacher getTeacher(long id) {
        return checkNonNull(teacherRepository.findOne(id), "Teacher not found");
    }


    private static <T> T checkNonNull(T o, String msg) {
        if (o == null) {
            throw new IllegalStateException(msg);
        }
        return o;
    }

}
