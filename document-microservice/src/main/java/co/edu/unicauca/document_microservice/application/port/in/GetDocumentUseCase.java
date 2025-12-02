package co.edu.unicauca.document_microservice.application.port.in;

import co.edu.unicauca.document_microservice.domain.model.Document;

public interface GetDocumentUseCase {

    Document getDocumentById(Long id);
    byte[] getFileBytes(Long id) throws Exception;
}