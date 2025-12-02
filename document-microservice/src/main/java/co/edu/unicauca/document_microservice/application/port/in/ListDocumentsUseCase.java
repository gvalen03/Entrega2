package co.edu.unicauca.document_microservice.application.port.in;

import co.edu.unicauca.document_microservice.domain.model.Document;
import co.edu.unicauca.document_microservice.domain.model.DocumentType;

import java.util.List;

public interface ListDocumentsUseCase {
    List<Document> listByProject(Long projectId);
    List<Document> listByType(Long projectId, DocumentType documentType);
}
