package co.edu.unicauca.document_microservice.infrastructure.adapter.out.messaging;

import co.edu.unicauca.document_microservice.domain.model.DocumentStatus;
import co.edu.unicauca.document_microservice.domain.model.DocumentType;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import co.edu.unicauca.document_microservice.application.port.out.SendNotificationPort;
import org.springframework.stereotype.Component;

@Component
public class DocumentNotificationProducer implements SendNotificationPort {

    private final RabbitTemplate template;

    public DocumentNotificationProducer(RabbitTemplate template) {
        this.template = template;
    }

    @Override
    public void sendDocumentUploaded(DocumentNotification notification) {
        template.convertAndSend("document.exchange", "document.uploaded", notification);
    }

    @Override
    public void sendDocumentDeleted(Long documentId, Long projectId, DocumentType documentType) {
        // 1. Crear el objeto DTO de notificación con el estado "DELETED"
        DocumentNotification notification = new DocumentNotification(
                documentId,projectId,documentType,
                DocumentStatus.DELETED.name()
        );

        // 2. Enviar el objeto DTO a RabbitMQ usando una routing key específica para eliminación
        template.convertAndSend("document.exchange", "document.uploaded", notification);
    }
}
