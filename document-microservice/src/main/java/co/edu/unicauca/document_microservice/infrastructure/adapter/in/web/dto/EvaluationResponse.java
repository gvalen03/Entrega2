package co.edu.unicauca.document_microservice.infrastructure.adapter.in.web.dto;

public class EvaluationResponse {
    private Long id;
    private Long reviewerId;
    private String reviewerRole;

    private String status;    // APPROVED, REJECTED
    private String comments;
    private String evaluatedAt;
}
