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

// Clase que representa la entidad Subject (materia)
public class Subject {
    // ID único de la materia
    private int subjectId;

    // Nombre de la materia
    private String name;

    // ID del profesor que imparte la materia
    private int professorId;
}
