// http://localhost:8080/schedule-subject
package com.miApi.studentsapi.controller;

import com.miApi.studentsapi.model.ScheduleSubject;
import com.miApi.studentsapi.service.ScheduleSubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Controlador REST para la entidad ScheduleSubject
@RestController
@RequestMapping("/scheduleSubject")
public class ScheduleSubjectController {

    @Autowired
    private ScheduleSubjectService scheduleSubjectService;

    // ✅ GET /schedule-subject → lista completa
    @GetMapping
    public List<ScheduleSubject> getAll() {
        return scheduleSubjectService.getAllScheduleSubjects();
    }

    // ✅ GET /schedule-subject/{id} → por ID
    @GetMapping("/{id}")
    public ResponseEntity<ScheduleSubject> getById(@PathVariable int id) {
        ScheduleSubject scheduleSubject= scheduleSubjectService.getScheduleSubjectById(id);
        if(scheduleSubject != null){
            return ResponseEntity.ok(scheduleSubject);
        }else{
            return ResponseEntity.notFound().build();
        }
    }
    // ✅ POST /schedule-subject → agregar
    @PostMapping
    public ResponseEntity<ScheduleSubject> addScheduleSubject(@RequestBody ScheduleSubject scheduleSubject) {
        scheduleSubjectService.addScheduleSubject(scheduleSubject);
        if(scheduleSubject != null){
            return ResponseEntity.status(HttpStatus.CREATED).body(scheduleSubject); // retornar el estatus 201
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    // ✅ PUT /schedule-subject → actualizar
    @PutMapping
    public ResponseEntity<String> updateScheduleSubject(@RequestBody ScheduleSubject scheduleSubject) {
        if (scheduleSubjectService.updateScheduleSubject(scheduleSubject)){
            return ResponseEntity.ok().body("Successfully updated");
        }else{
            return ResponseEntity.badRequest().body("Invalid input");
        }
    }

    // ✅ DELETE /schedule-subject/{id} → eliminar
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable int id) {
        if( scheduleSubjectService.deleteScheduleSubjectById(id)){
            return ResponseEntity.ok().body("Deleted");
        }else{
            return ResponseEntity.internalServerError().body("Not found");
        }
    }
}
