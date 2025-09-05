// http://localhost:8080/subject
package com.miApi.studentsapi.controller;

import com.miApi.studentsapi.model.Subject;
import com.miApi.studentsapi.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Controlador REST para la entidad Subject
@RestController
@RequestMapping("/subject")
public class SubjectController {

    @Autowired
    private SubjectService subjectService;

    // ✅ GET /subject → lista completa de materias
    @GetMapping
    public List<Subject> getAll() {
        return subjectService.getAllSubjects();
    }

    // ✅ GET /subject/{id} → materia por ID
    @GetMapping("/{id}")
    public Subject getById(@PathVariable int id) {
        return subjectService.getSubjectById(id);
    }

    // ✅ POST /subject → agregar nueva materia
    @PostMapping
    public void add(@RequestBody Subject subject) {
        subjectService.addSubject(subject);
    }

    // ✅ PUT /subject → actualizar materia
    @PutMapping
    public boolean update(@RequestBody Subject subject) {
        return subjectService.updateSubject(subject);
    }

    // ✅ DELETE /subject/{id} → eliminar materia
    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable int id) {
        return subjectService.deleteSubjectById(id);
    }
}
