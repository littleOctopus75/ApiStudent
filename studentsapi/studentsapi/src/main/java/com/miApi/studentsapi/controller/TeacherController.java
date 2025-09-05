// http://localhost:8080/teacher
package com.miApi.studentsapi.controller;

import com.miApi.studentsapi.model.Teacher;
import com.miApi.studentsapi.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Controlador REST para la entidad Teacher
@RestController
@RequestMapping("/teacher")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    // ✅ GET /teacher → lista de profesores
    @GetMapping
    public List<Teacher> getAll() {
        return teacherService.getAllTeachers();
    }

    // ✅ GET /teacher/{id} → profesor por ID
    @GetMapping("/{id}")
    public Teacher getById(@PathVariable int id) {
        return teacherService.getTeacherById(id);
    }

    // ✅ POST /teacher → nuevo profesor
    @PostMapping
    public void add(@RequestBody Teacher teacher) {
        teacherService.addTeacher(teacher);
    }

    // ✅ PUT /teacher → actualizar profesor
    @PutMapping
    public boolean update(@RequestBody Teacher teacher) {
        return teacherService.updateTeacher(teacher);
    }

    // ✅ DELETE /teacher/{id} → eliminar profesor
    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable int id) {
        return teacherService.deleteTeacherById(id);
    }
}
