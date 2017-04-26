package com.vbelova.teachers.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "universities")
public class University implements CategoryEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    @NotNull @Size(min = 3, message = "University name is too short")
    public String name;
    @NotNull @Size(min = 10, message = "Address is too short")
    public String address;
    @Column(name = "city_id")
    public Long cityId;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "category: university\nname: " + name + "\naddress: " + address;
    }
}
