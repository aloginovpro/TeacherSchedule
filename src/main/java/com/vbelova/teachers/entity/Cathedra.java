package com.vbelova.teachers.entity;

import javax.persistence.*;

@Entity
@Table(name = "cathedras")
public class Cathedra {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;
    public String name;
    @Column(name = "faculty_id")
    public Long facultyId;

}
