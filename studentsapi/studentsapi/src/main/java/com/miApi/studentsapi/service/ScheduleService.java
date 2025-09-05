package com.miApi.studentsapi.service;

import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.miApi.studentsapi.model.Schedule;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Esta clase es un servicio (@Service) que gestiona la lógica del negocio
 * para la entidad Schedule. Aquí se realizan las operaciones CRUD usando un
 * archivo JSON en lugar de base de datos.
 */
@Service
public class ScheduleService {

    // Ruta del archivo donde se almacenarán los horarios en formato JSON
    private final String FILE_PATH = "schedule.json";

    // ObjectMapper es una clase de Jackson que permite convertir entre objetos Java
    // y JSON
    private final ObjectMapper objectMapper = new ObjectMapper();

    // Lista en memoria donde se guardan los horarios temporalmente
    private List<Schedule> schedules = new ArrayList<>();

    /**
     * Constructor: cuando se crea una instancia de ScheduleService,
     * se llama al método loadSchedules() para cargar los datos existentes desde el
     * archivo.
     */
    public ScheduleService() {
        loadSchedules();
    }

    /**
     * Carga los horarios desde el archivo JSON al iniciar el servicio.
     * Si el archivo no existe, la lista queda vacía.
     */
    private void loadSchedules() {
        try {
            File file = new File(FILE_PATH);
            if (file.exists()) {
                schedules = objectMapper.readValue(file, new TypeReference<List<Schedule>>() {
                });
            }
        } catch (IOException e) {
            e.printStackTrace(); // Manejo simple de errores
        }
    }

    /**
     * Guarda el contenido actual de la lista de horarios al archivo JSON.
     * Se llama después de cada operación de escritura (crear, actualizar,
     * eliminar).
     * 
     * @throws IOException
     * @throws DatabindException
     * @throws StreamWriteException
     */
    private void saveSchedules() throws StreamWriteException, DatabindException, IOException {

        objectMapper
                .writerWithDefaultPrettyPrinter()
                .writeValue(new File(FILE_PATH), schedules);

    }

    /**
     * Devuelve todos los horarios almacenados.
     * 
     * @return lista completa de horarios.
     */
    public List<Schedule> getAllSchedules() {
        return schedules;
    }

    /**
     * Devuelve todos los horarios
     * 
     * @return lista con todos los horarios
     */
    public List<Schedule> getSchedules() {
        return schedules;
    }

    /**
     * Busca y devuelve un horario según su ID.
     * 
     * @param id identificador único
     * @return horario encontrado o null si no existe
     */
    public Schedule getScheduleById(int id) {
        return schedules.stream()
                .filter(schedule -> schedule.getScheduleId() == id)
                .findFirst()
                .orElse(null);
    }

    /**
     * Agrega un nuevo horario a la lista y guarda el cambio en el archivo.
     * 
     * @param schedule nuevo horario a agregar
     */
    public boolean addSchedule(Schedule schedule) {
        schedules.add(schedule);
        try {
            saveSchedules();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * Actualiza un horario existente que tenga el mismo ID.
     * Si lo encuentra, lo reemplaza y guarda el archivo.
     * 
     * @param updatedSchedule objeto con los datos nuevos
     * @return true si se actualizó correctamente, false si no se encontró
     */
    public boolean updateSchedule(Schedule updatedSchedule) {
        for (int i = 0; i < schedules.size(); i++) {
            Schedule current = schedules.get(i);
            if (current.getScheduleId() == updatedSchedule.getScheduleId()) {
                schedules.set(i, updatedSchedule);
                try {
                    saveSchedules();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return true;
            }
        }
        return false;
    }

    /**
     * Elimina un horario de la lista por su ID.
     * 
     * @param id del horario a eliminar
     * @return true si se eliminó, false si no se encontró
     */
    public boolean deleteScheduleById(int id) {
        boolean removed = schedules.removeIf(schedule -> schedule.getScheduleId() == id);
        if (removed) {
            try {
                saveSchedules();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return removed;
    }
}
