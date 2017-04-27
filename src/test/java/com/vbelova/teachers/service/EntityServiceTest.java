package com.vbelova.teachers.service;

import com.vbelova.teachers.entity.City;
import com.vbelova.teachers.entity.University;
import com.vbelova.teachers.repository.*;
import org.junit.Before;
import org.junit.Test;

import java.util.Collections;
import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class EntityServiceTest {

    private EntityService entityService;
    private CityRepository cityRepository;
    private UniversityRepository universityRepository;
    private City moscow;
    private University mephi;

    @Before
    public void init() {
        cityRepository = mock(CityRepository.class);
        moscow = new City();
        moscow.id = 1L;
        moscow.name = "Moscow";
        when(cityRepository.findAll()).thenReturn(asList(moscow, new City()));

        universityRepository = mock(UniversityRepository.class);
        mephi = new University();
        mephi.name = "MEPHI";
        when(universityRepository.findByCityId(moscow.id)).thenReturn(Collections.singletonList(mephi));

        entityService = new EntityServiceImpl(
                cityRepository,
                universityRepository,
                mock(FacultyRepository.class),
                mock(CathedraRepository.class),
                mock(TeacherRepository.class)
        );
    }

    @Test
    public void testGetEntityList() {
        List<City> cities = entityService.getCities();
        assertEquals(2, cities.size());
        assertEquals(moscow, cities.get(0));
        verify(cityRepository).findAll();

        List<University> universities = entityService.getUniversities(moscow.id);
        assertEquals(1, universities.size());
        assertEquals(mephi.name, universities.get(0).name);
        verify(universityRepository).findByCityId(moscow.id);
    }

    @Test
    public void testSave() {
        City city = new City();
        entityService.save(City.class, city);
        verify(cityRepository).save(city);
    }

    @Test
    public void testDelete() {
        entityService.delete(City.class, moscow.id);
        verify(cityRepository).delete(moscow.id);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testUnknownEntityClass() {
        entityService.get(Object.class, 1);
    }

}
