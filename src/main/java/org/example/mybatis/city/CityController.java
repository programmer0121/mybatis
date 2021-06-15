package org.example.mybatis.city;


import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cities")
@AllArgsConstructor
public class CityController {

    private final CityService service;

    @GetMapping
    public List<City> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public City getOne(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    public void addCity(@RequestBody City city) {
       service.insert(city);
    }
}
