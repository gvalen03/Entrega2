package co.edu.unicauca.document_microservice.application.service;

import co.edu.unicauca.document_microservice.application.port.in.UploadDocumentUseCase;
import co.edu.unicauca.document_microservice.application.port.out.SaveDocumentPort;
import co.edu.unicauca.document_microservice.application.port.out.SendNotificationPort;
import co.edu.unicauca.document_microservice.domain.model.Document;
import co.edu.unicauca.document_microservice.domain.model.DocumentStatus;
import co.edu.unicauca.document_microservice.domain.model.DocumentType;
import co.edu.unicauca.document_microservice.domain.model.ValidationResult;
import co.edu.unicauca.document_microservice.domain.service.DocumentValidator;
import co.edu.unicauca.document_microservice.domain.service.factory.DocumentFactory;
import co.edu.unicauca.document_microservice.infrastructure.adapter.in.web.dto.DocumentRequest;
import co.edu.unicauca.document_microservice.infrastructure.adapter.out.messaging.DocumentNotification;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class UploadDocumentService implements UploadDocumentUseCase {

    private final SaveDocumentPort saveDocumentPort;
    private final SendNotificationPort sendNotificationPort;
    private final DocumentFactory documentFactory;

    public UploadDocumentService(
            SaveDocumentPort saveDocumentPort,
            SendNotificationPort sendNotificationPort,
            List<DocumentValidator> validators, DocumentFactory documentFactory
    ) {
        this.saveDocumentPort = saveDocumentPort;
        this.sendNotificationPort = sendNotificationPort;
        this.documentFactory = documentFactory;
    }

    @Override
    public Document uploadDocument(DocumentRequest request) throws Exception {

        // 1. Extraer datos del DTO
        MultipartFile file = request.getFile();
        byte[] fileBytes = file.getBytes();
        String originalFileName = file.getOriginalFilename();
        String extension = getExtension(originalFileName);

        // 2. VALIDACIÓN
        List<DocumentValidator> specificValidators = documentFactory.crearValidators(request.getDocumentType());

        for (DocumentValidator v : specificValidators) {
            ValidationResult result = v.validate(fileBytes, extension);
            if (result.isInvalid()) {
                throw new IllegalArgumentException(result.getMessage());
            }
        }

        // 3. CONSTRUIR AGREGADO DOMAIN (id=null para que la BD lo genere)
        Document document = new Document(
                null,
                request.getProjectId(), // String [cite: 46]
                DocumentType.valueOf(request.getDocumentType()),
                DocumentStatus.PENDING,
                originalFileName,
                "TEMP_ROUTE",
                LocalDateTime.now()
        );

        // 4. GUARDAR DOCUMENTO (adapter OUT devuelve el Document de dominio CON el ID generado)
        Document savedDocument = saveDocumentPort.save(document, fileBytes);

        // 5. NOTIFICAR (Se mapea el Document de dominio a DocumentNotification ANTES de enviar)
        DocumentNotification notification = new DocumentNotification(
                savedDocument.getId(),
                savedDocument.getProjectId(),
                savedDocument.getType(),
                savedDocument.getStatus().name()
        );
        sendNotificationPort.sendDocumentUploaded(notification);

        return savedDocument;
    }

    private String getExtension(String fileName) {
        if (fileName == null || !fileName.contains(".")) return "";
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }

}