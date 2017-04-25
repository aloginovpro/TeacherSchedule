package com.vbelova.teachers.entity;

import lombok.Getter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "cities")
public class City implements CategoryEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    @NotNull @Size(min = 3, message = "City name is too short")
    @Getter
    public String name;

    @Override
    public String toString() {
        return "category: city\nname: " + name;
    }

}
