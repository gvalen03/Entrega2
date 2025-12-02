package co.edu.unicauca.document_microservice.domain.service;

import co.edu.unicauca.document_microservice.domain.model.Document;
import co.edu.unicauca.document_microservice.domain.model.ValidationResult;

public interface DocumentValidator {
    ValidationResult validate(byte[] fileBytes, String extension);

}
