package co.edu.unicauca.document_microservice.application.port.out;

import co.edu.unicauca.document_microservice.domain.model.Document;
import co.edu.unicauca.document_microservice.domain.model.DocumentType;

import java.util.List;
import java.util.Optional;

public interface LoadDocumentPort {
    Document loadById(Long documentId);
    List<Document> loadByType(Long projectId, DocumentType documentType);
    byte[] loadFileBytesById(Long documentId) throws Exception;
    List<Document> loadByProject(Long projectId);
}