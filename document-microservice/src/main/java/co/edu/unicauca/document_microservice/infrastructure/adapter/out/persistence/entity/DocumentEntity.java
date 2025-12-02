package co.edu.unicauca.document_microservice.infrastructure.adapter.out.persistence.entity;

import co.edu.unicauca.document_microservice.domain.model.DocumentStatus;
import co.edu.unicauca.document_microservice.domain.model.DocumentType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "documents")
public class DocumentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Column(name = "project_id", nullable = false)
    private Long projectId;

    @Setter
    @Enumerated(EnumType.STRING)
    @Column(name = "document_type", nullable = false)
    private DocumentType type;

    @Setter
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private DocumentStatus status;

    @Setter
    @Column(name = "file_name", nullable = false)
    private String fileName;

    @Setter
    @Column(name = "file_url", nullable = false)
    private String fileUrl;

    @Setter
    @Column(name = "uploaded_at", nullable = false)
    private LocalDateTime uploadedAt;

    // ----------------------------------------------------
    // Constructors
    // ----------------------------------------------------

    public DocumentEntity() {
    }

    public DocumentEntity(Long projectId, DocumentType type, DocumentStatus status,
                          String fileName, String fileUrl, LocalDateTime uploadedAt) {
        this.projectId = projectId;
        this.type = type;
        this.status = status;
        this.fileName = fileName;
        this.fileUrl = fileUrl;
        this.uploadedAt = uploadedAt;
    }

    // ----------------------------------------------------
    // Getters and Setters
    // ----------------------------------------------------

}
