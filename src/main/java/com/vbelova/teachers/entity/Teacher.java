package com.vbelova.teachers.entity;

import lombok.Getter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "teachers")
public class Teacher implements CategoryEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    @NotNull @Size(min = 3, message = "Teacher name is too short")
    @Getter
    public String name;
    @NotNull @Size(message = "Experience can not be negative")
    public Integer experience;
    @NotNull @Size(min = 20, message = "Teacher can not be that young")
    public Integer age;
    @NotNull @Size(min = 5, message = "Email is too short")
    public String email;
    @Column(name = "cathedra_id")
    public Long cathedraId;

    @Override
    public String toString() {
        return String.format(
                "category: teacher\nname: %s\nexperience: %s y\nage: %s y\nemail: %s\n",
                name, experience, age, email
        );
    }
}
