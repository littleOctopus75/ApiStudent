// El "package" indica la carpeta lógica donde vive esta clase.
package com.miApi.studentsapi.model;

// Anotaciones Lombok para evitar código repetitivo
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// @Data genera automáticamente getters, setters, toString, equals y hashCode
@Data

// Constructor vacío requerido por Spring
@NoArgsConstructor

// Constructor con todos los atributos
@AllArgsConstructor

// Clase que representa la relación fuerte ScheduleSubject
public class ScheduleSubject {
    // ID único del horario-materia
    private int scheduleSubjectId;

    // FK al horario
    private int scheduleId;

    // FK a la materia
    private int subjectId;

    // Hora de inicio
    private String startTime;

    // Hora de fin
    private String endTime;

    // Aula donde se imparte
    private String classroom;

    // Día de la semana
    private String day;
}
