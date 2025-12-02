package co.edu.unicauca.document_microservice.application.service;

import co.edu.unicauca.document_microservice.application.port.in.GetDocumentUseCase;
import co.edu.unicauca.document_microservice.application.port.out.LoadDocumentPort;
import co.edu.unicauca.document_microservice.domain.model.Document;
import org.springframework.stereotype.Service;

@Service
public class GetDocumentService implements GetDocumentUseCase {
    private final LoadDocumentPort loadDocumentPort;

    public GetDocumentService(LoadDocumentPort loadDocumentPort) {
        this.loadDocumentPort = loadDocumentPort;
    }

    @Override
    public Document getDocumentById(Long documentId) {
        return loadDocumentPort.loadById(documentId);
    }

    @Override
    public byte[] getFileBytes(Long documentId) throws Exception {
        return loadDocumentPort.loadFileBytesById(documentId);
    }
}
