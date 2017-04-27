package com.vbelova.teachers.service;

import com.vbelova.teachers.entity.Subject;
import com.vbelova.teachers.repository.ScheduleItemRepository;
import com.vbelova.teachers.repository.SubjectRepository;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;

import static java.util.Collections.singletonList;
import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.*;

public class ScheduleServiceTest {

    private ScheduleService scheduleService;
    private SubjectRepository subjectRepository;
    private ScheduleItemRepository scheduleItemRepository;

    private Subject maths = createSubject(1, "maths");

    @Before
    public void init() {
        subjectRepository = mock(SubjectRepository.class);
        when(subjectRepository.findByTeacherId(anyLong())).thenReturn(singletonList(maths));

        scheduleItemRepository = mock(ScheduleItemRepository.class);
        scheduleService = new ScheduleServiceImpl(subjectRepository, scheduleItemRepository);
    }

    @Test
    public void testGetSubjects() {
        Map<Long, String> subjects = scheduleService.getSubjects(1);
        verify(subjectRepository).findByTeacherId(1);
        assertEquals(1, subjects.size());
        assertEquals(subjects.get(maths.id), maths.name);
    }

    @Test
    public void testUpdateSubjects() {
        scheduleService.updateSubjects(1, singletonList("physics"));
        verify(subjectRepository).delete(maths.id);
        verify(subjectRepository).save(anyCollection());
    }

    private static Subject createSubject(long id, String name) {
        Subject subject = new Subject(name, 1L);
        subject.id = id;
        return subject;
    }

}
