package co.edu.unicauca.document_microservice.factory;

import co.edu.unicauca.document_microservice.utilities.validator.DocumentoValidator;
import co.edu.unicauca.document_microservice.utilities.validator.PdfValidator;
import co.edu.unicauca.document_microservice.utilities.validator.WordValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class DocumentoFactory {

    private final Map<String, List<DocumentoValidator>> validators = new HashMap<>();

    @Autowired
    public DocumentoFactory(List<DocumentoValidator> validatorList) {

        validators.put("FORMATO_A", List.of(
                getValidator(validatorList, PdfValidator.class),
                getValidator(validatorList, WordValidator.class)
        ));

        validators.put("ANTEPROYECTO", List.of(
                getValidator(validatorList, PdfValidator.class)
        ));

        validators.put("CARTA_EMPRESA", List.of(
                getValidator(validatorList, PdfValidator.class)
        ));

        validators.put("INFORME_FINAL", List.of(
                getValidator(validatorList, WordValidator.class)
        ));
    }

    private DocumentoValidator getValidator(List<DocumentoValidator> validatorList,
                                            Class<? extends DocumentoValidator> clazz) {
        return validatorList.stream()
                .filter(clazz::isInstance)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Validator no encontrado: " + clazz.getSimpleName()));
    }

    public List<DocumentoValidator> crearValidators(String tipoDocumento) {
        List<DocumentoValidator> lista = validators.get(tipoDocumento);
        if (lista == null || lista.isEmpty()) {
            throw new IllegalArgumentException("Tipo de documento no soportado: " + tipoDocumento);
        }
        return lista;
    }
}