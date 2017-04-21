package com.vbelova.teachers.entity;

import javax.persistence.*;

@Entity
@Table(name = "universities")
public class University {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    public String name;
    public String address;
    @Column(name = "city_id")
    public Long cityId;

}
