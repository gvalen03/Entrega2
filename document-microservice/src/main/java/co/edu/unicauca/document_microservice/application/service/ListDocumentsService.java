package co.edu.unicauca.document_microservice.application.service;

import co.edu.unicauca.document_microservice.application.port.in.ListDocumentsUseCase;
import co.edu.unicauca.document_microservice.application.port.out.LoadDocumentPort;
import co.edu.unicauca.document_microservice.domain.model.Document;
import co.edu.unicauca.document_microservice.domain.model.DocumentType;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListDocumentsService implements ListDocumentsUseCase {
    private final LoadDocumentPort loadDocumentPort;

    public ListDocumentsService(LoadDocumentPort loadDocumentPort) {
        this.loadDocumentPort = loadDocumentPort;
    }

    @Override
    public List<Document> listByProject(Long projectId) {
        return loadDocumentPort.loadByProject(projectId);
    }

    @Override
    public List<Document> listByType(Long projectId, DocumentType documentType) {
        return loadDocumentPort.loadByType(projectId, documentType);
    }
}
