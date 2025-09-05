package com.miApi.studentsapi.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.miApi.studentsapi.model.Teacher;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Servicio que gestiona operaciones CRUD de la entidad Teacher
 * usando almacenamiento en archivo JSON.
 */
@Service
public class TeacherService {

    private final String FILE_PATH = "teachers.json";
    private final ObjectMapper objectMapper = new ObjectMapper();
    private List<Teacher> teachers = new ArrayList<>();

    public TeacherService() {
        loadTeachers();
    }

    private void loadTeachers() {
        try {
            File file = new File(FILE_PATH);
            if (file.exists()) {
                teachers = objectMapper.readValue(file, new TypeReference<List<Teacher>>() {});
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveTeachers() {
        try {
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(FILE_PATH), teachers);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Teacher> getAllTeachers() {
        return teachers;
    }

    public Teacher getTeacherById(int id) {
        return teachers.stream()
                .filter(t -> t.getTeacherId() == id)
                .findFirst()
                .orElse(null);
    }

    public void addTeacher(Teacher teacher) {
        teachers.add(teacher);
        saveTeachers();
    }

    public boolean updateTeacher(Teacher updated) {
        for (int i = 0; i < teachers.size(); i++) {
            if (teachers.get(i).getTeacherId() == updated.getTeacherId()) {
                teachers.set(i, updated);
                saveTeachers();
                return true;
            }
        }
        return false;
    }

    public boolean deleteTeacherById(int id) {
        boolean removed = teachers.removeIf(t -> t.getTeacherId() == id);
        if (removed) {
            saveTeachers();
        }
        return removed;
    }
}
