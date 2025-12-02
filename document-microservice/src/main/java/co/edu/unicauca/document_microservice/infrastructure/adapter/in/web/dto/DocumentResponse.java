package co.edu.unicauca.document_microservice.infrastructure.adapter.in.web.dto;

import co.edu.unicauca.document_microservice.domain.model.DocumentType;

public class DocumentResponse {
    private Long id;
    private Long projectId;

    private DocumentType type;          // FORMATO_A, ANTEPROYECTO, MONOGRAFIA, CARTA_EMPRESA
    private String state;               // UPLOADED, UNDER_REVIEW, APPROVED, REJECTED

    private String filename;
    private String url;                 // ubicación en Storage (S3, local, etc)
    private Long size;                  // tamaño del archivo en bytes

    private String uploadedAt;          // fecha de subida
    private String lastUpdateAt;        // última modificación de estado

    // Evaluación asociada (si existe)
    private EvaluationResponse evaluation;

    // Información extra que sirve para dashboards
    private String reviewerRole;        // DIRECTOR, COORDINADOR, JURADO
    private Long reviewerId;
}
