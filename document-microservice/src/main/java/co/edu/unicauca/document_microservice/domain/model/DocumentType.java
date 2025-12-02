package co.edu.unicauca.document_microservice.domain.model;

import java.util.stream.Stream;

public enum DocumentType {

    FORMATO_A,
    ANTEPROYECTO,
    CARTA_EMPRESA,
    MONOGRAFIA
    ;

    /**
     * Metodo de Fábrica Estático para obtener DocumentType a partir de una cadena de texto.
     * Centraliza la lógica de búsqueda y el manejo de excepciones.
     * * @param value La cadena de texto (ej., "FORMATO_A") que se desea convertir.
     * @return La instancia de DocumentType correspondiente.
     * @throws IllegalArgumentException si el valor no coincide con ningún tipo.
     */
    public static DocumentType fromValue(String value) {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException("El tipo de documento no puede ser nulo o vacío.");
        }

        // Se asegura de que la búsqueda sea insensible a mayúsculas/minúsculas
        String upperCaseValue = value.trim().toUpperCase();

        // Utiliza Stream para buscar el valor que coincide
        return Stream.of(DocumentType.values())
                .filter(type -> type.name().equals(upperCaseValue))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Tipo de documento no válido: " + value));
    }
}