package com.vbelova.teachers.entity;

import javax.persistence.*;

@Entity
@Table(name = "schedule_items")
public class ScheduleItem {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    public Integer day;
    public Integer hour;
    @Column(name = "teacher_id")
    public Long teacherId;
    @Column(name = "subject_id")
    public Long subjectId;

}
