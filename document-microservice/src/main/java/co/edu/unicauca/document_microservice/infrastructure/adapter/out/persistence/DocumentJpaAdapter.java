package co.edu.unicauca.document_microservice.infrastructure.adapter.out.persistence;

import co.edu.unicauca.document_microservice.domain.model.Document;
import co.edu.unicauca.document_microservice.domain.model.DocumentType;
import co.edu.unicauca.document_microservice.infrastructure.adapter.out.persistence.entity.DocumentEntity;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public class DocumentJpaAdapter {
    private final DocumentJpaRepository jpa;
    private final DocumentMapper mapper;

    public DocumentJpaAdapter(DocumentJpaRepository jpa, DocumentMapper mapper) {
        this.jpa = jpa;
        this.mapper = mapper;
    }
    // metadata

    public Document save(Document document) {
        DocumentEntity entity = mapper.toEntity(document);
        DocumentEntity saved = jpa.save(entity);
        return mapper.toDomain(saved);
    }

    public Optional<Document> findById(Long id) {
        return jpa.findById(id)
                .map(mapper::toDomain);
    }

    public List<Document> findByProjectId(Long projectId) {
        return jpa.findByProjectId(projectId)
                .stream()
                .map(mapper::toDomain)
                .toList();
    }

    public List<Document> findByProjectIdAndType(Long projectId, DocumentType type) {
        return jpa.findByProjectIdAndType(projectId,type)
                .stream()
                .map(mapper::toDomain)
                .toList();
    }

    public void updateFileUrl(Long documentId, String fileUrl) {
        DocumentEntity entity = jpa.findById(documentId)
                .orElseThrow(() -> new RuntimeException("Document not found"));

        entity.setFileUrl(fileUrl); // usar setter del JPA Entity
        jpa.save(entity);            // actualizar
    }

    /**
     * Deletes a document entity from the database by its ID.
     * Implements the delete operation for the DeleteDocumentAdapter.
     */
    public void deleteById(Long id) {
        jpa.deleteById(id);
    }
}