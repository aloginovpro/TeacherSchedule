package com.vbelova.teachers.entity;

import lombok.Getter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "universities")
public class University implements CategoryEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    @NotNull @Size(min = 3, message = "University name is too short")
    @Getter
    public String name;
    @NotNull @Size(min = 10, message = "Address is too short")
    public String address;
    @Column(name = "city_id")
    public Long cityId;

    @Override
    public String toString() {
        return "name: " + name + "\naddress: " + address;
    }
}
