package co.edu.unicauca.document_microservice.application.port.out;

import co.edu.unicauca.document_microservice.domain.model.Document;

import java.util.Optional;

public interface DeleteDocumentPort {
    Optional<Document> findById(Long documentId);
    void delete(Long documentId);

}
