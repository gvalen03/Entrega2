package co.edu.unicauca.document_microservice.application.port.in;

import java.util.List;

import co.edu.unicauca.document_microservice.domain.model.Document;
import co.edu.unicauca.document_microservice.infrastructure.adapter.in.web.dto.DocumentRequest;

public interface UploadDocumentUseCase {

    Document uploadDocument(DocumentRequest request) throws Exception;
}
