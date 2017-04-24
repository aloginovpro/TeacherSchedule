package com.vbelova.teachers.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "teachers")
public class Teacher {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    @NotNull
    @Size(min = 3, message = "Teacher name is too short")
    public String name;
    @NotNull @Size(min = 4, message = "Subjects list is too short")
    public String subjects;
    @NotNull @Size(message = "Experience can not be negative")
    public Integer experience;
    @NotNull @Size(min = 20, message = "Teacher can not be that young")
    public Integer age;
    @NotNull @Size(min = 5, message = "Email is too short")
    public String email;
    @Column(name = "cathedra_id")
    public Long cathedraId;

}
