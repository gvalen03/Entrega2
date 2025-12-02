package co.edu.unicauca.document_microservice.domain.model;

import lombok.Getter;

public class ValidationResult {

    private final boolean valid;
    @Getter
    private final String message;

    public ValidationResult(boolean valid, String message) {
        this.valid = valid;
        this.message = message;
    }

    //Factory Method

    // Verifica el estado del objeto.
    public boolean isInvalid() {
        return !this.valid;
    }

    // Fabrica: resultado válido
    public static ValidationResult ok() { // Se recomienda usar 'static' en métodos fábrica
        return new ValidationResult(true, null);
    }

    // Fabrica: resultado inválido
    public static ValidationResult invalid(String message) { // Se recomienda usar 'static'
        return new ValidationResult(false, message);
    }
}
