package com.vbelova.teachers.entity;

import lombok.Getter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "faculties")
public class Faculty implements CategoryEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    @NotNull @Size(min = 1, message = "Faculty name is too short")
    @Getter
    public String name;
    @Column(name = "university_id")
    public Long universityId;

    @Override
    public String toString() {
        return "category: faculty\nname: " + name;
    }
}
