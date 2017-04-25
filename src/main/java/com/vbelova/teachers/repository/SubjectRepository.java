package com.vbelova.teachers.repository;

import com.vbelova.teachers.entity.Subject;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubjectRepository extends CrudRepository<Subject, Long> {

    List<Subject> findByTeacherId(long id);

}