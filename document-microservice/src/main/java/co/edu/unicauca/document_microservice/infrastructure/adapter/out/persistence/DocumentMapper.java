package co.edu.unicauca.document_microservice.infrastructure.adapter.out.persistence;

import co.edu.unicauca.document_microservice.domain.model.Document;
import co.edu.unicauca.document_microservice.infrastructure.adapter.out.persistence.entity.DocumentEntity;
import org.springframework.stereotype.Component;

@Component
public class DocumentMapper {
    public DocumentEntity toEntity(Document doc) {
        return new DocumentEntity(
                doc.getProjectId(),
                doc.getType(),
                doc.getStatus(),
                doc.getFileName(),
                doc.getFileUrl(),
                doc.getUploadedAt()
        );
    }

    public Document toDomain(DocumentEntity entity) {
        return new Document(
                entity.getId(),
                entity.getProjectId(),
                entity.getType(),
                entity.getStatus(),
                entity.getFileName(),
                entity.getFileUrl(),
                entity.getUploadedAt()
        );
    }
}
