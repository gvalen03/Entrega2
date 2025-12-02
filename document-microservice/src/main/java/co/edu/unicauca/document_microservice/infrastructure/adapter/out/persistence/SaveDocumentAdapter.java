package co.edu.unicauca.document_microservice.infrastructure.adapter.out.persistence;

import co.edu.unicauca.document_microservice.application.port.out.SaveDocumentPort;
import co.edu.unicauca.document_microservice.domain.model.Document;
import co.edu.unicauca.document_microservice.infrastructure.adapter.out.storage.FileStorageService;
import org.springframework.stereotype.Component;

@Component
public class SaveDocumentAdapter implements SaveDocumentPort {

    private final DocumentJpaAdapter documentJpaAdapter;
    private final FileStorageService fileStorageService;

    public SaveDocumentAdapter(DocumentJpaAdapter documentJpaAdapter, FileStorageService fileStorageService) {
        this.documentJpaAdapter = documentJpaAdapter;
        this.fileStorageService = fileStorageService;
    }

    @Override
    public Document save(Document document, byte[] fileBytes) throws Exception {

        // PASO 1: Guardar Metadatos Iniciales para obtener el ID
        // Esto crea el registro en BD y nos devuelve el objeto con el ID asignado.
        Document savedMetadata = documentJpaAdapter.save(document);

        String finalFileUrl = null;
        try {
            // Recuperamos el ID del objeto ya guardado
            Long documentId = savedMetadata.getId();

            // PASO 2: Guardar el Archivo Físico
            // Usamos el ID para nombrar el archivo físico
            String storageFileName = documentId + "_" + savedMetadata.getFileName();
            finalFileUrl = fileStorageService.saveFile(fileBytes, storageFileName);

            // PASO 3: Actualizar SOLO la URL en la BD
            // No creamos un nuevo documento, solo actualizamos el campo URL del ID existente
            documentJpaAdapter.updateFileUrl(documentId, finalFileUrl);

            // PASO 4: Devolver el Objeto Final
            return new Document(
                    savedMetadata.getId(),
                    savedMetadata.getProjectId(),
                    savedMetadata.getType(),
                    savedMetadata.getStatus(),
                    savedMetadata.getFileName(),
                    finalFileUrl, // URL actualizada
                    savedMetadata.getUploadedAt());

        } catch (Exception e) {
            // Rollback manual del archivo físico si falla la actualización de la BD
            if (finalFileUrl != null) {
                fileStorageService.deleteFile(finalFileUrl);
            }
            throw e;
        }
    }
}