// El "package" indica la carpeta lógica donde vive esta clase.
// Aquí estamos diciendo que Schedule pertenece al paquete model dentro de tu proyecto.
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

// Aquí comienza tu clase Schedule.
public class Schedule {
    // id único para cada horario.
    private int scheduleId;

    // Nombre del horario (por ejemplo, "Horario A", "Vespertino", etc.).
    private String name;

    // Periodo académico (por ejemplo, "2025-1").
    private String period;

    // Semestre al que corresponde el horario.
    private int semester;

    // llave foránea de carrera a la que pertenece el horario.
    private int careerId;
}
