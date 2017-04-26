package com.vbelova.teachers.entity;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "teachers")
public class Teacher implements CategoryEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    @NotNull @Size(min = 3, message = "Teacher name is too short")
    public String name;
    @NotNull @Min(value = 0, message = "Experience can not be negative")
    public Integer experience;
    @NotNull @Min(value = 20, message = "Teacher can not be that young")
    public Integer age;
    @NotNull @Size(min = 5, message = "Email is too short")
    public String email;
    @NotNull @Size(min = 5, message = "Regalia is too short")
    public String regalia;
    @Column(name = "cathedra_id")
    public Long cathedraId;

    @Override
    public String getName() {
        return name;
    }

}
