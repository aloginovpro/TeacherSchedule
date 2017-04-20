package com.vbelova.teachers.repository;

import com.vbelova.teachers.entity.University;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UniversityRepository extends CrudRepository<University, Long> {
    List<University> findByCityId(long id);
}