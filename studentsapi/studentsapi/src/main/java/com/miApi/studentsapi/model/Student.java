// El "package" indica la carpeta lógica donde vive esta clase.
// Aquí estamos diciendo que Student pertenece al paquete model dentro de tu proyecto.
package com.miApi.studentsapi.model;

// Estas anotaciones vienen de Lombok. Son librerías que nos evitan escribir mucho código repetitivo.
import lombok.AllArgsConstructor;  // Crea automáticamente un constructor con todos los atributos.
import lombok.Data;                // Crea automáticamente getters, setters, toString, equals y hashCode.
import lombok.NoArgsConstructor;   // Crea automáticamente un constructor vacío (sin parámetros).

// @Data genera automáticamente:
// - Getters (getNombre(), getId(), etc.)
// - Setters (setNombre(), setId(), etc.)
// - Método toString()
// - Métodos equals() y hashCode()
@Data

// @NoArgsConstructor crea un constructor vacío (sin argumentos).
// Necesario para que Spring pueda crear objetos automáticamente en algunas operaciones (por ejemplo, cuando recibe JSONs).
@NoArgsConstructor

// @AllArgsConstructor crea un constructor que recibe como parámetros TODOS los atributos de la clase.
@AllArgsConstructor

// Aquí comienza tu clase Student.
public class Student {
    // id único para cada estudiante.
    private int studentId;

    // Nombre completo del estudiante.
    private String fullName;

    // Matrícula (tuition number) única del estudiante.
    private String tuition;
    
    // Número de semestre que cursa (de 1 a 10).
    private Integer semester;

    // Estado del estudiante (enrolled = cursando, graduated = egresado).
    private String status;

    // llave foranea de carrera que estudia el alumno.
    private int careerId;

    // llave foranea de horario
    private int scheduleId;

}
