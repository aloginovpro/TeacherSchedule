package com.vbelova.teachers.repository;

import com.vbelova.teachers.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends CrudRepository<User, Long> {

    User findByName(String name);

}