package co.edu.unicauca.document_microservice.domain.service;
import co.edu.unicauca.document_microservice.domain.model.ValidationResult;
import org.springframework.stereotype.Component;

@Component
public class PdfValidator implements DocumentValidator {

    @Override
    public ValidationResult validate(byte[] fileBytes, String extension) {
        if (!extension.equalsIgnoreCase("pdf")) {
            return ValidationResult.invalid("El archivo no es un pdf");
        }
        return ValidationResult.ok();
    }
}