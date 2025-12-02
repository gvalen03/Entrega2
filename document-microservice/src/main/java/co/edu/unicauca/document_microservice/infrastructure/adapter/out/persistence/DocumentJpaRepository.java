package co.edu.unicauca.document_microservice.infrastructure.adapter.out.persistence;

import co.edu.unicauca.document_microservice.domain.model.DocumentType;
import co.edu.unicauca.document_microservice.infrastructure.adapter.out.persistence.entity.DocumentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DocumentJpaRepository extends JpaRepository<DocumentEntity, Long> {
    List<DocumentEntity> findByProjectId(Long projectId);
    List<DocumentEntity> findByProjectIdAndType(Long projectId, DocumentType type);
}
