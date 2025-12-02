package co.edu.unicauca.document_microservice.infrastructure.adapter.out.persistence;

import co.edu.unicauca.document_microservice.application.port.out.LoadDocumentPort;
import co.edu.unicauca.document_microservice.domain.model.Document;
import co.edu.unicauca.document_microservice.domain.model.DocumentType;
import co.edu.unicauca.document_microservice.infrastructure.adapter.out.persistence.entity.DocumentEntity;
import co.edu.unicauca.document_microservice.infrastructure.adapter.out.storage.FileStorageService;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;

@Component
public class LoadDocumentAdapter implements LoadDocumentPort {
    private final DocumentJpaAdapter documentJpaAdapter;
    private final DocumentMapper mapper;
    private final FileStorageService fileStorageService;

    public LoadDocumentAdapter(
            DocumentJpaAdapter documentJpaAdapter,
            DocumentMapper mapper,
            FileStorageService fileStorageService
    ) {
        this.documentJpaAdapter = documentJpaAdapter;
        this.mapper = mapper;
        this.fileStorageService = fileStorageService;
    }

    // Metodo auxiliar para manejar la conversión de String a Long (si el JpaAdapter lo requiere)
    private Long parseProjectId(String projectId) {
        try {
            return Long.valueOf(projectId);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("El ID del proyecto debe ser un número válido: " + projectId);
        }
    }

    @Override
    public Document loadById(Long documentId) {
        // La llamada a findById del JpaAdapter ya devuelve Optional<Document>
        // No necesitas la variable 'entity' ni llamar al mapper de nuevo.

        return documentJpaAdapter.findById(documentId)
                .orElseThrow(() -> new NoSuchElementException("Documento no encontrado con ID: " + documentId));
    }

    @Override
    public List<Document> loadByType(Long projectId, DocumentType documentType) {
        return documentJpaAdapter.findByProjectIdAndType(projectId, documentType);
    }

    @Override
    public List<Document> loadByProject(Long projectId) {
        return documentJpaAdapter.findByProjectId(projectId);
    }


    @Override
    public byte[] loadFileBytesById(Long documentId) throws Exception {
        // 1. Obtener Metadatos para encontrar la URL del archivo
        Document document = loadById(documentId);

        if (document.getFileUrl() == null || document.getFileUrl().equals("TEMP_ROUTE")) {
            throw new IOException("El documento no tiene una URL de archivo válida para descargar.");
        }

        // 2. Usar el servicio de almacenamiento para leer los bytes
        return fileStorageService.readFile(document.getFileUrl());
    }
}
