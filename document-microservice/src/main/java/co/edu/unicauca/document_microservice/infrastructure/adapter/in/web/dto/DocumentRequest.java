package co.edu.unicauca.document_microservice.infrastructure.adapter.in.web.dto;

import co.edu.unicauca.document_microservice.domain.model.DocumentType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
@Schema(description = "Datos para subir un documento")
public class DocumentRequest {
    @Schema(description = "ID del proyecto", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long projectId;

    @Schema(description = "Tipo de documento", example = "FORMATO_A", requiredMode = Schema.RequiredMode.REQUIRED)
    private String documentType;

    @Schema(description = "Archivo a subir", requiredMode = Schema.RequiredMode.REQUIRED)
    private MultipartFile file;
}