package com.vbelova.teachers.entity;

import javax.persistence.*;

@Entity
@Table(name = "cities")
public class City {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    public String name;

}
