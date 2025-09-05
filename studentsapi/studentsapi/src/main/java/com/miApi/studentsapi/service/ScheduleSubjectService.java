package com.miApi.studentsapi.service;

import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.miApi.studentsapi.model.ScheduleSubject;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Servicio que gestiona operaciones CRUD de la entidad ScheduleSubject
 * usando almacenamiento en archivo JSON.
 */
@Service
public class ScheduleSubjectService {

    private final String FILE_PATH = "scheduleSubjects.json";
    private final ObjectMapper objectMapper = new ObjectMapper();
    private List<ScheduleSubject> scheduleSubjects = new ArrayList<>();

    public ScheduleSubjectService() {
        loadScheduleSubjects();
    }

    private void loadScheduleSubjects() {
        try {
            File file = new File(FILE_PATH);
            if (file.exists()) {
                scheduleSubjects = objectMapper.readValue(file, new TypeReference<List<ScheduleSubject>>() {
                });
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveScheduleSubjects() throws StreamWriteException, DatabindException, IOException {
        objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(FILE_PATH), scheduleSubjects);

    }

    public List<ScheduleSubject> getAllScheduleSubjects() {
        return scheduleSubjects;
    }

    public ScheduleSubject getScheduleSubjectById(int id) {
        return scheduleSubjects.stream()
                .filter(s -> s.getScheduleSubjectId() == id)
                .findFirst()
                .orElse(null);
    }

    public ScheduleSubject addScheduleSubject(ScheduleSubject scheduleSubject) {
        scheduleSubjects.add(scheduleSubject);
        try {
            saveScheduleSubjects();
            return scheduleSubject;

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean updateScheduleSubject(ScheduleSubject updated) {
        for (int i = 0; i < scheduleSubjects.size(); i++) {
            if (scheduleSubjects.get(i).getScheduleSubjectId() == updated.getScheduleSubjectId()) {
                scheduleSubjects.set(i, updated);
                try {
                    saveScheduleSubjects();
                    return true;

                } catch (IOException e) {
                    e.printStackTrace();
                    return false;
                }
            }
        }
        return false;
    }

    public boolean deleteScheduleSubjectById(int id) {
        boolean removed = scheduleSubjects.removeIf(s -> s.getScheduleSubjectId() == id);
        if (removed) {
            try {
                saveScheduleSubjects();
                return true;

            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }
        return removed;
    }
}
