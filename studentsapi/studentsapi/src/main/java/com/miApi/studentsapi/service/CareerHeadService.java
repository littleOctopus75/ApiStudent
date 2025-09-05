package com.miApi.studentsapi.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.miApi.studentsapi.model.CareerHead;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Servicio que gestiona operaciones CRUD de la entidad CareerHead
 * usando almacenamiento en archivo JSON.
 */
@Service
public class CareerHeadService {

    private final String FILE_PATH = "careerHeads.json";
    private final ObjectMapper objectMapper = new ObjectMapper();
    private List<CareerHead> careerHeads = new ArrayList<>();

    public CareerHeadService() {
        loadCareerHeads();
    }

    private void loadCareerHeads() {
        try {
            File file = new File(FILE_PATH);
            if (file.exists()) {
                careerHeads = objectMapper.readValue(file, new TypeReference<List<CareerHead>>() {});
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveCareerHeads() {
        try {
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(FILE_PATH), careerHeads);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<CareerHead> getAllCareerHeads() {
        return careerHeads;
    }

    public CareerHead getCareerHeadById(int id) {
        return careerHeads.stream()
                .filter(h -> h.getHeadId() == id)
                .findFirst()
                .orElse(null);
    }

    public void addCareerHead(CareerHead head) {
        careerHeads.add(head);
        saveCareerHeads();
    }

    public boolean updateCareerHead(CareerHead updated) {
        for (int i = 0; i < careerHeads.size(); i++) {
            if (careerHeads.get(i).getHeadId() == updated.getHeadId()) {
                careerHeads.set(i, updated);
                saveCareerHeads();
                return true;
            }
        }
        return false;
    }

    public boolean deleteCareerHeadById(int id) {
        boolean removed = careerHeads.removeIf(h -> h.getHeadId() == id);
        if (removed) {
            saveCareerHeads();
        }
        return removed;
    }
}
