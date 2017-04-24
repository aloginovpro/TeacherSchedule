package com.vbelova.teachers.entity;

import lombok.Getter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "cathedras")
public class Cathedra implements CategoryEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    @NotNull @Size(min = 3, message = "Cathedra name is too short")
    @Getter
    public String name;
    @Column(name = "faculty_id")
    public Long facultyId;

    @Override
    public String toString() {
        return "name: " + name;
    }

}
