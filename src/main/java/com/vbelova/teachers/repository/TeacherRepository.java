package com.vbelova.teachers.repository;

import com.vbelova.teachers.entity.Teacher;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeacherRepository extends CrudRepository<Teacher, Long> {

    List<Teacher> findByCathedraId(long id);

}