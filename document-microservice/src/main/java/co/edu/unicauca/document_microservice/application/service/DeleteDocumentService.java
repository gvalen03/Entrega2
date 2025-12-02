package co.edu.unicauca.document_microservice.application.service;

import co.edu.unicauca.document_microservice.application.port.in.DeleteDocumentUseCase;
import co.edu.unicauca.document_microservice.application.port.out.DeleteDocumentPort;
import co.edu.unicauca.document_microservice.application.port.out.SendNotificationPort;
import co.edu.unicauca.document_microservice.domain.model.Document;
import co.edu.unicauca.document_microservice.infrastructure.adapter.out.storage.FileStorageService;
import co.edu.unicauca.document_microservice.infrastructure.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DeleteDocumentService implements DeleteDocumentUseCase {

    private final DeleteDocumentPort deleteDocumentPort;
    private final FileStorageService fileStorageService;
    private final SendNotificationPort sendNotificationPort;

    public DeleteDocumentService(
            DeleteDocumentPort deleteDocumentPort,
            FileStorageService fileStorageService,
            SendNotificationPort sendNotificationPort
    ) {
        this.deleteDocumentPort = deleteDocumentPort;
        this.fileStorageService = fileStorageService;
        this.sendNotificationPort = sendNotificationPort;
    }

    @Override
    @Transactional
    public void deleteDocument(Long documentId) throws Exception {

        // 1. Buscar el documento para obtener la URL del archivo
        Document documentToDelete = deleteDocumentPort.findById(documentId)
                .orElseThrow(() -> new ResourceNotFoundException("Document not found with ID: " + documentId));

        String fileUrl = documentToDelete.getFileUrl();

        try {
            // 2. Eliminar el registro de metadatos de la DB
            deleteDocumentPort.delete(documentId);

            // 3. Eliminar el archivo físico (Si el archivo existe)
            if (fileUrl != null && !fileUrl.isEmpty() && !fileUrl.equals("TEMP_ROUTE")) {
                fileStorageService.deleteFile(fileUrl);
            }

            // 4. Notificar la eliminación (Implementar el metodo sendDocumentDeleted en SendNotificationPort)
            sendNotificationPort.sendDocumentDeleted(documentId, documentToDelete.getProjectId(),documentToDelete.getType() );

        } catch (Exception e) {
            // Si falla la eliminación del registro o del archivo, se lanza la excepción
            throw new Exception("Failed to delete document with ID: " + documentId, e);
        }
    }
}