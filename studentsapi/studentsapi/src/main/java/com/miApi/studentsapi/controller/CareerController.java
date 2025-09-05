// http://localhost:8080/career
package com.miApi.studentsapi.controller;

import com.miApi.studentsapi.model.Career;
import com.miApi.studentsapi.service.CareerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Controlador REST para la entidad Career
@RestController
@RequestMapping("/career")
public class CareerController {

    @Autowired
    private CareerService careerService;

    // ✅ GET /career → lista de carreras
    @GetMapping
    public List<Career> getAll() {
        return careerService.getAllCareers();
    }

    // ✅ GET /career/{id} → carrera por ID
    @GetMapping("/{id}")
    public Career getById(@PathVariable int id) {
        return careerService.getCareerById(id);
    }

    // ✅ POST /career → nueva carrera
    @PostMapping
    public void add(@RequestBody Career career) {
        careerService.addCareer(career);
    }

    // ✅ PUT /career → actualizar carrera
    @PutMapping
    public boolean update(@RequestBody Career career) {
        return careerService.updateCareer(career);
    }

    // ✅ DELETE /career/{id} → eliminar carrera
    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable int id) {
        return careerService.deleteCareerById(id);
    }
}
