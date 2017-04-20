package com.vbelova.teachers.repository;

import com.vbelova.teachers.entity.Cathedra;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CathedraRepository extends CrudRepository<Cathedra, Long> {

    List<Cathedra> findByFacultyId(long id);

}