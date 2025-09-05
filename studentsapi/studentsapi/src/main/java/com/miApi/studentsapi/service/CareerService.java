package com.miApi.studentsapi.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.miApi.studentsapi.model.Career;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Servicio que gestiona operaciones CRUD de la entidad Career
 * usando almacenamiento en archivo JSON.
 */
@Service
public class CareerService {

    private final String FILE_PATH = "careers.json";
    private final ObjectMapper objectMapper = new ObjectMapper();
    private List<Career> careers = new ArrayList<>();

    public CareerService() {
        loadCareers();
    }

    private void loadCareers() {
        try {
            File file = new File(FILE_PATH);
            if (file.exists()) {
                careers = objectMapper.readValue(file, new TypeReference<List<Career>>() {});
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveCareers() {
        try {
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(FILE_PATH), careers);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Career> getAllCareers() {
        return careers;
    }

    public Career getCareerById(int id) {
        return careers.stream()
                .filter(c -> c.getCareerId() == id)
                .findFirst()
                .orElse(null);
    }

    public void addCareer(Career career) {
        careers.add(career);
        saveCareers();
    }

    public boolean updateCareer(Career updated) {
        for (int i = 0; i < careers.size(); i++) {
            if (careers.get(i).getCareerId() == updated.getCareerId()) {
                careers.set(i, updated);
                saveCareers();
                return true;
            }
        }
        return false;
    }

    public boolean deleteCareerById(int id) {
        boolean removed = careers.removeIf(c -> c.getCareerId() == id);
        if (removed) {
            saveCareers();
        }
        return removed;
    }
}
