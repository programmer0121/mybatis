package org.example.mybatis.city;

import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CityService {

    @Insert("INSERT INTO city (name, state, country) VALUES(#{name}, #{state}, #{country})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(City city);

    @Select("SELECT id, name, state, country FROM city WHERE id = #{id}")
    City findById(long id);

    @Select("SELECT id, name, state, country FROM city")
    List<City> findAll();

    @Update("UPDATE city " +
            "SET name = #{name}, " +
            "    state = #{state}, " +
            "    country = #{country}" +
            "WHERE id = #{id}")
    void update(City city);

    @Delete("DELETE FROM city WHERE id = #{id}")
    void delete(long id);

}
