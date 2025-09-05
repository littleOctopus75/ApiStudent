// http://localhost:8080/student
package com.miApi.studentsapi.controller;

import com.miApi.studentsapi.model.Student;
import com.miApi.studentsapi.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Indica que esta clase es un controlador REST que maneja peticiones HTTP
@RestController
@RequestMapping("/student") // Ruta base para todos los endpoints
public class StudentController {

    // Inyectamos el servicio para acceder a los métodos de negocio
    @Autowired
    private StudentService studentService;

    // GET /student -lista completa
    @GetMapping
    public List<Student> getAll() {
        return studentService.getAllStudents();
    }

    // GET /student/tuition/{tuition} → un estudiante por matrícula
    @GetMapping("/tuition/{tuition}")
    public Student getStudentByTuition(@PathVariable String tuition) {
        return studentService.getStudentByTuition(tuition);
    }

    // POST /student → agrega nuevo estudiante
    @PostMapping
    public ResponseEntity<Student> addStudent(@RequestBody Student student) {
        if( studentService.addStudent(student)){
            return ResponseEntity.ok().body(student);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    // PUT /student → actualiza un estudiante por matrícula
    @PutMapping
    public ResponseEntity<String> updateStudent(@RequestBody Student student) {
        if (studentService.updateStudent(student)) {
            return ResponseEntity.ok().body("Successful operation");
        } else {
            return ResponseEntity.badRequest().body("Student not found");
        }

    }

    // DELETE /student/tuition/{tuition} → elimina estudiante por matrícula
    @DeleteMapping("/tuition/{tuition}")
    public String deleteStudent(@PathVariable String tuition) {
        if (studentService.deleteStudentByTuition(tuition)) {
            return "Successful operation";
        } else {
            return "Student not found";
        }

    }
}
