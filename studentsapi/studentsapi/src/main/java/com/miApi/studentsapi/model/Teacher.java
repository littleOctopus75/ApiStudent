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

// Clase que representa la entidad Teacher (profesor)
public class Teacher {
    // ID único del profesor
    private int teacherId;

    // Nombre completo del profesor
    private String fullName;

    // ID de la carrera a la que pertenece
    private int careerId;
}
