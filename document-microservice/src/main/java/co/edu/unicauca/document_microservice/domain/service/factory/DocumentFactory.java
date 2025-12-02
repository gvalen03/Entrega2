package co.edu.unicauca.document_microservice.domain.service.factory;

import co.edu.unicauca.document_microservice.domain.service.DocumentValidator;
import co.edu.unicauca.document_microservice.domain.service.PdfValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class DocumentFactory {

    private final Map<String, List<DocumentValidator>> validators = new HashMap<>();
    private final DocumentValidator pdfValidator; // Referencia para el validador de PDF

    @Autowired
    public DocumentFactory(List<DocumentValidator> validatorList) {

        // 1. Obtener la única instancia de PdfValidator que necesitamos
        this.pdfValidator = getValidator(validatorList, PdfValidator.class);

        // Crear la lista que SOLO contiene el validador de PDF
        List<DocumentValidator> soloPdf = List.of(this.pdfValidator);

        // 2. Asignar esta lista simplificada a todos los tipos de documentos
        validators.put("FORMATO_A", soloPdf); // <-- Solo valida PDF
        validators.put("ANTEPROYECTO", soloPdf); // <-- Solo valida PDF
        validators.put("CARTA_EMPRESA", soloPdf); // <-- Solo valida PDF
        validators.put("MONOGRAFIA", soloPdf); // <-- Solo valida PDF

        // Es recomendable incluir todos los valores de tu enum DocumentType aquí.
    }

    private DocumentValidator getValidator(List<DocumentValidator> validatorList,
                                           Class<? extends DocumentValidator> clazz) {
        return validatorList.stream()
                .filter(clazz::isInstance)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Validator no encontrado: " + clazz.getSimpleName()));
    }

    public List<DocumentValidator> crearValidators(String tipoDocumento) {
        List<DocumentValidator> lista = validators.get(tipoDocumento);
        if (lista == null || lista.isEmpty()) {
            throw new IllegalArgumentException("Tipo de documento no soportado: " + tipoDocumento);
        }
        return lista;
    }
}