package com.miApi.studentsapi.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.miApi.studentsapi.model.Subject;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Servicio que gestiona operaciones CRUD de la entidad Subject
 * usando almacenamiento en archivo JSON.
 */
@Service
public class SubjectService {

    private final String FILE_PATH = "subjects.json";
    private final ObjectMapper objectMapper = new ObjectMapper();
    private List<Subject> subjects = new ArrayList<>();

    public SubjectService() {
        loadSubjects();
    }

    private void loadSubjects() {
        try {
            File file = new File(FILE_PATH);
            if (file.exists()) {
                subjects = objectMapper.readValue(file, new TypeReference<List<Subject>>() {});
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveSubjects() {
        try {
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(FILE_PATH), subjects);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Subject> getAllSubjects() {
        return subjects;
    }

    public Subject getSubjectById(int id) {
        return subjects.stream()
                .filter(s -> s.getSubjectId() == id)
                .findFirst()
                .orElse(null);
    }

    public void addSubject(Subject subject) {
        subjects.add(subject);
        saveSubjects();
    }

    public boolean updateSubject(Subject updated) {
        for (int i = 0; i < subjects.size(); i++) {
            if (subjects.get(i).getSubjectId() == updated.getSubjectId()) {
                subjects.set(i, updated);
                saveSubjects();
                return true;
            }
        }
        return false;
    }

    public boolean deleteSubjectById(int id) {
        boolean removed = subjects.removeIf(s -> s.getSubjectId() == id);
        if (removed) {
            saveSubjects();
        }
        return removed;
    }
}
