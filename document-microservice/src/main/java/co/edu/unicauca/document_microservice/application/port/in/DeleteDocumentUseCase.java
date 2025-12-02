package co.edu.unicauca.document_microservice.application.port.in;

public interface DeleteDocumentUseCase {
    void deleteDocument(Long documentId) throws Exception;
}
