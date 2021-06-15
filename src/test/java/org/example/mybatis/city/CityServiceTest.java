package org.example.mybatis.city;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CityServiceTest {

    @Autowired
    private CityService service;

    @Test
    void testFindAll() {
        List<City> cities = service.findAll();
        assertEquals(cities.size(), 2);
    }

    @Test
    void testFindById() {
        assertNull(service.findById(3));
        City city = service.findById(2);
        assertNotNull(city);
        assertEquals(2, city.getId());
        assertEquals("Poland", city.getCountry());
        assertEquals("Warsaw", city.getName());
        assertEquals("mazowieckie", city.getState());
    }

    @Test
    @Transactional
    @Rollback
    void testInsert() {
        City city = City.builder().name("Bydgoszcz").country("Poland").state("kujawsko-pomorskie").build();
        assertEquals(0, city.getId());
        service.insert(city);
        assertNotEquals(0, city.getId());
        City city2 = service.findById(city.getId());
        assertEquals(city.getId(), city2.getId());
        assertEquals("Bydgoszcz", city2.getName());
        assertEquals("kujawsko-pomorskie", city2.getState());
        assertEquals("Poland", city2.getCountry());
    }

    @Test
    @Transactional
    @Rollback
    void testUpdate() {
        City city = service.findById(2);
        assertEquals("Warsaw", city.getName());
        city.setName("Warszawa");
        service.update(city);
        City updated = service.findById(2);
        assertEquals("Warszawa", updated.getName());
    }

    @Test
    @Transactional
    @Rollback
    void testDelete() {
        City city = service.findById(2);
        assertNotNull(city);
        service.delete(city.getId());
        assertNull(service.findById(2));

    }

}