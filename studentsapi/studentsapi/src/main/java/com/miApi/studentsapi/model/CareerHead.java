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

// Clase que representa la entidad CareerHead (jefe de carrera)
public class CareerHead {
    // ID único del jefe de carrera
    private int headId;

    // ID del profesor que ocupa el puesto
    private int professorId;
}
