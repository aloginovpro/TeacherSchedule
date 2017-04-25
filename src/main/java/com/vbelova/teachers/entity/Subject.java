package com.vbelova.teachers.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "subjects")
public class Subject {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    @NotNull @Size(min = 3, message = "Subject name is too short")
    public String name;
    @Column(name = "teacher_id")
    public Long teacherId;

}
