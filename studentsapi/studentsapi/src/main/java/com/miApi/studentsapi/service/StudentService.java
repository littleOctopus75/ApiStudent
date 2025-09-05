package com.miApi.studentsapi.service;

import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.miApi.studentsapi.model.Student;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Esta clase es un servicio (@Service) que gestiona la lógica del negocio
 * para la entidad Student. Aquí se realizan las operaciones CRUD usando un
 * archivo JSON en lugar de base de datos.
 */
@Service
public class StudentService {

    // Ruta del archivo donde se almacenarán los estudiantes en formato JSON
    private final String FILE_PATH = "students.json";

    // ObjectMapper es una clase de Jackson que permite convertir entre objetos Java
    // y JSON
    private final ObjectMapper objectMapper = new ObjectMapper();

    // Lista en memoria donde se guardan los estudiantes temporalmente
    private List<Student> students = new ArrayList<>();

    /**
     * Constructor: cuando se crea una instancia de StudentService,
     * se llama al método loadStudents() para cargar los datos existentes desde el
     * archivo.
     */
    public StudentService() {
        loadStudents();
    }

    /**
     * Carga los estudiantes desde el archivo JSON al iniciar el servicio.
     * Si el archivo no existe, la lista queda vacía.
     */
    private void loadStudents() {
        try {
            File file = new File(FILE_PATH);
            if (file.exists()) {
                students = objectMapper.readValue(file, new TypeReference<List<Student>>() {
                });
            }
        } catch (IOException e) {
            e.printStackTrace(); // Manejo simple de errores
        }
    }

    /**
     * Guarda el contenido actual de la lista de estudiantes al archivo JSON.
     * Se llama después de cada operación de escritura (crear, actualizar,
     * eliminar).
     * 
     * @throws IOException
     * @throws DatabindException
     * @throws StreamWriteException
     */
    private void saveStudents() throws StreamWriteException, DatabindException, IOException {
        objectMapper
                .writerWithDefaultPrettyPrinter() // Da formato legible al archivo JSON
                .writeValue(new File(FILE_PATH), students);

    }

    /**
     * Devuelve todos los estudiantes almacenados.
     * 
     * @return lista completa de estudiantes.
     */
    public List<Student> getAllStudents() {
        return students;
    }

    /**
     * Busca y devuelve un estudiante según su matrícula.
     * 
     * @param tuition matrícula única
     * @return estudiante encontrado o null si no existe
     */
    public Student getStudentByTuition(String tuition) {
        return students.stream()
                .filter(student -> student.getTuition().equals(tuition))
                .findFirst()
                .orElse(null); // que es lo de atras
    }

    /**
     * Agrega un nuevo estudiante a la lista y guarda el cambio en el archivo.
     * 
     * @param student nuevo estudiante a agregar
     */
    public boolean addStudent(Student student) {
        students.add(student);
        try {
            saveStudents();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return true;
        }
    }

    /**
     * Actualiza un estudiante existente que tenga la misma matrícula.
     * Si lo encuentra, lo reemplaza y guarda el archivo.
     * 
     * @param updatedStudent objeto con los datos nuevos
     * @return true si se actualizó correctamente, false si no se encontró
     */
    public boolean updateStudent(Student updatedStudent) {
        for (int i = 0; i < students.size(); i++) {
            Student current = students.get(i);
            if (current.getTuition().equals(updatedStudent.getTuition())) {
                students.set(i, updatedStudent); // Reemplaza el objeto
                try {
                    saveStudents();
                    return true;
                } catch (IOException e) {
                    e.printStackTrace();
                    return false;
                }
            }
        }
        return false; // Si no encontró coincidencia
    }

    /**
     * Elimina un estudiante de la lista por su matrícula.
     * 
     * @param tuition matrícula del estudiante a eliminar
     * @return true si se eliminó, false si no se encontró
     */
    public boolean deleteStudentByTuition(String tuition) {
        boolean removed = students.removeIf(student -> student.getTuition().equals(tuition));
        if (removed) {
            saveStudents();
        }
        return removed;
    }
}
