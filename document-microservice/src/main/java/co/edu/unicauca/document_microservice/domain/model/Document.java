package co.edu.unicauca.document_microservice.domain.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
public class Document {

    // Getters y setters
    private Long id;
    private Long projectId;
    private DocumentType type;
    @Setter
    private DocumentStatus status;
    private String fileName;
    private String fileUrl;
    private LocalDateTime uploadedAt;

    public Document(Long id,
                    Long projectId,
                    DocumentType type,
                    DocumentStatus status,
                    String fileName,
                    String fileUrl,
                    LocalDateTime uploadedAt) {
        this.id = id;
        this.projectId = projectId;
        this.type = type;
        this.status = status;
        this.fileName = fileName;
        this.fileUrl = fileUrl;
        this.uploadedAt = uploadedAt;
    }

}