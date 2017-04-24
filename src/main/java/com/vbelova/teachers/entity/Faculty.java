package com.vbelova.teachers.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "faculties")
public class Faculty {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    @NotNull @Size(min = 1, message = "Faculty name is too short")
    public String name;
    @Column(name = "university_id")
    public Long universityId;

}
