package co.edu.unicauca.document_microservice.infrastructure.adapter.out.messaging;

import co.edu.unicauca.document_microservice.domain.model.DocumentType;

import java.time.LocalDateTime;

public class DocumentNotification {

    private Long documentId;
    private Long projectId;
    private DocumentType documentType;
    private String status;
    private LocalDateTime timestamp;

    public DocumentNotification(Long documentId,
                                Long projectId,
                                DocumentType documentType,
                                String status) {
        this.documentId = documentId;
        this.projectId = projectId;
        this.documentType = documentType;
        this.status = status;
        this.timestamp = LocalDateTime.now();
    }

    public Long getDocumentId() { return documentId; }
    public Long getProjectId() { return projectId; }
    public DocumentType getDocumentType() { return documentType; }
    public String getStatus() { return status; }
    public LocalDateTime getTimestamp() { return timestamp; }
}

