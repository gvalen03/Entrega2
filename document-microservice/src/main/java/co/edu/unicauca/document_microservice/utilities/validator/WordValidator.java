package co.edu.unicauca.document_microservice.utilities.validator;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class WordValidator implements DocumentoValidator {
    @Override
    public void validar(MultipartFile archivo) {
        String nombre = archivo.getOriginalFilename();
        if (nombre == null || !(nombre.toLowerCase().endsWith(".docx") || nombre.toLowerCase().endsWith(".doc"))) {
            throw new IllegalArgumentException("El archivo debe ser un Word (.doc o .docx).");
        }
    }
}