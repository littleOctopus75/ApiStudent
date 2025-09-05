// http://localhost:8080/schedule
package com.miApi.studentsapi.controller;

import com.miApi.studentsapi.model.Schedule;
import com.miApi.studentsapi.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Indica que esta clase es un controlador REST que maneja peticiones HTTP
@RestController
@RequestMapping("/schedule") // Ruta base para todos los endpoints
public class ScheduleController {

    // Inyectamos el servicio para acceder a los métodos de negocio
    @Autowired
    private ScheduleService scheduleService;

    // ✅ GET /schedule lista completa
    @GetMapping
    public List<Schedule> getSchedules() {
        return scheduleService.getSchedules();
    }

    // ✅ GET /schedule/{id} → obtiene un horario por ID
    @GetMapping("/{id}")
    public ResponseEntity<Schedule> getScheduleById(@PathVariable int id) {
        Schedule schedule = scheduleService.getScheduleById(id);

        if (schedule != null) {
            return ResponseEntity.ok(schedule); // 200 con el objeto
        } else {
            return ResponseEntity.notFound().build(); // 404 vacío
        }
    }

    // ✅ POST /schedule → agrega un nuevo horario
    @PostMapping
    public ResponseEntity<Schedule> addSchedule(@RequestBody Schedule schedule) {
        boolean response = scheduleService.addSchedule(schedule);
        if (response != false) {
            return ResponseEntity.status(HttpStatus.CREATED).body(schedule); // 200 con el objeto
        } else {
            return ResponseEntity.notFound().build(); // 404 vacío
        }
    }

    // ✅ PUT /schedule → actualiza un horario existente
    @PutMapping
    public ResponseEntity<Schedule> updateSchedule(@RequestBody Schedule schedule) {
        boolean response = scheduleService.updateSchedule(schedule);
        if (response != false) {
            return ResponseEntity.ok(schedule); // 200 con el objeto
        } else {
            return ResponseEntity.notFound().build(); // 404 vacío
        }
    }

    // ✅ DELETE /schedule/{id} → elimina un horario por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSchedule(@PathVariable int id) {
        boolean response = scheduleService.deleteScheduleById(id);
        if (response != false) {
            return ResponseEntity.ok("Schedule deleted"); // 200 con el objeto
        } else {
            return ResponseEntity.notFound().build(); // 404 vacío
        }

    }
}
