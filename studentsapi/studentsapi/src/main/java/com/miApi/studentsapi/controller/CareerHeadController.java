// http://localhost:8080/career-head
package com.miApi.studentsapi.controller;

import com.miApi.studentsapi.model.CareerHead;
import com.miApi.studentsapi.service.CareerHeadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Controlador REST para la entidad CareerHead
@RestController
@RequestMapping("/careerHead")
public class CareerHeadController {

    @Autowired
    private CareerHeadService careerHeadService;

    // ✅ GET /career-head → lista de jefes de carrera
    @GetMapping
    public List<CareerHead> getAll() {
        return careerHeadService.getAllCareerHeads();
    }

    // ✅ GET /career-head/{id} → jefe por ID
    @GetMapping("/{id}")
    public CareerHead getById(@PathVariable int id) {
        return careerHeadService.getCareerHeadById(id);
    }

    // ✅ POST /career-head → nuevo jefe
    @PostMapping
    public void add(@RequestBody CareerHead careerHead) {
        careerHeadService.addCareerHead(careerHead);
    }

    // ✅ PUT /career-head → actualizar jefe
    @PutMapping
    public boolean update(@RequestBody CareerHead careerHead) {
        return careerHeadService.updateCareerHead(careerHead);
    }

    // ✅ DELETE /career-head/{id} → eliminar jefe
    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable int id) {
        return careerHeadService.deleteCareerHeadById(id);
    }
}
