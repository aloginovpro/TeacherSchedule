package com.vbelova.teachers.entity;

import javax.persistence.*;

@Entity
@Table(name = "teachers")
public class Teacher {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;
    public String name;
    public String subjects;
    public Integer experience;
    public Integer age;
    public String email;
    @Column(name = "cathedra_id")
    public Long cathedraId;

}
