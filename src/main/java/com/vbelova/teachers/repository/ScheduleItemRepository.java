package com.vbelova.teachers.repository;

import com.vbelova.teachers.entity.ScheduleItem;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleItemRepository extends CrudRepository<ScheduleItem, Long> {

    List<ScheduleItem> findByTeacherId(long id);

}