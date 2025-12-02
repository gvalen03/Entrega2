package co.edu.unicauca.document_microservice.domain.service;

import co.edu.unicauca.document_microservice.domain.model.ValidationResult;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class WordValidator implements DocumentValidator {

    @Override
    public ValidationResult validate(byte[] fileBytes, String extension) {
        String lowerCaseExtension = extension.toLowerCase();

        if (!lowerCaseExtension.equals("doc") && !lowerCaseExtension.equals("docx")) {
            return ValidationResult.invalid("El archivo no es un documento de Word (.doc o .docx).");
        }
        return ValidationResult.ok();
    }
}