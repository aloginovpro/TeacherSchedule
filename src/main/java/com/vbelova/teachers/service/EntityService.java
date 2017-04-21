package com.vbelova.teachers.service;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.vbelova.teachers.entity.*;
import com.vbelova.teachers.repository.*;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class EntityService {
    private final CityRepository cityRepository;
    private final UniversityRepository universityRepository;
    private final FacultyRepository facultyRepository;
    private final CathedraRepository cathedraRepository;
    private final TeacherRepository teacherRepository;
    private final Map<Class<?>, CrudRepository<?, Long>> classToRepositoryMap;

    public EntityService(
            CityRepository cityRepository,
            UniversityRepository universityRepository,
            FacultyRepository facultyRepository,
            CathedraRepository cathedraRepository,
            TeacherRepository teacherRepository
    ) {
        this.cityRepository = cityRepository;
        this.universityRepository = universityRepository;
        this.facultyRepository = facultyRepository;
        this.cathedraRepository = cathedraRepository;
        this.teacherRepository = teacherRepository;
        this.classToRepositoryMap = ImmutableMap.of(
                City.class, cityRepository,
                University.class, universityRepository,
                Faculty.class, facultyRepository,
                Cathedra.class, cathedraRepository,
                Teacher.class, teacherRepository
        );
    }



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


    public void delete(Class<?> clazz, long id) {
        findRepo(clazz).delete(id);
    }

    public <T> T get(Class<T> clazz, long id) {
        return checkNonNull(
                findRepo(clazz).findOne(id),
                "No entry found for " + clazz.getSimpleName() + " with id " + id
        );
    }

    @SuppressWarnings("unchecked")
    private <T> CrudRepository<T, Long> findRepo(Class<T> clazz) {
        CrudRepository<?, Long> repo = classToRepositoryMap.get(clazz);
        if (repo == null) {
            throw new IllegalArgumentException("Entity not supported: " + clazz);
        }
        return (CrudRepository<T, Long>) repo;
    }

    private static <T> T checkNonNull(T o, String msg) {
        if (o == null) {
            throw new IllegalStateException(msg);
        }
        return o;
    }

}
