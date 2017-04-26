package com.vbelova.teachers.entity;

import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "schedule_items")
@NoArgsConstructor
public class ScheduleItem {

    @EmbeddedId
    public Pk pk;

    @Column(name = "subject_id")
    public Long subjectId;

    @Embeddable
    public static class Pk implements Serializable {
        public Integer day;
        public Integer hour;
        @Column(name = "teacher_id")
        public Long teacherId;
    }

    public ScheduleItem(int day, int hour, long teacherId, long subjectId) {
        this.subjectId = subjectId;
        pk = new Pk();
        pk.day = day;
        pk.hour = hour;
        pk.teacherId = teacherId;
    }

}
