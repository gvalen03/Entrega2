package co.edu.unicauca.document_microservice.infrastructure.adapter.out.persistence;

import co.edu.unicauca.document_microservice.application.port.out.DeleteDocumentPort;
import co.edu.unicauca.document_microservice.domain.model.Document;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class DeleteDocumentAdapter implements DeleteDocumentPort {

    private final DocumentJpaAdapter documentJpaAdapter;

    public DeleteDocumentAdapter(DocumentJpaAdapter documentJpaAdapter) {
        this.documentJpaAdapter = documentJpaAdapter;
    }

    @Override
    public Optional<Document> findById(Long documentId) {
        return documentJpaAdapter.findById(documentId);
    }

    @Override
    public void delete(Long documentId) {
        documentJpaAdapter.deleteById(documentId);
    }
}