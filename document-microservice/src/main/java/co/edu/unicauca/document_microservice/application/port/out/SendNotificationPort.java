package co.edu.unicauca.document_microservice.application.port.out;

import co.edu.unicauca.document_microservice.domain.model.DocumentType;
import co.edu.unicauca.document_microservice.infrastructure.adapter.out.messaging.DocumentNotification;

public interface SendNotificationPort {
    void sendDocumentUploaded(DocumentNotification notification);
    void sendDocumentDeleted(Long documentId, Long projectId, DocumentType documentType);
}
