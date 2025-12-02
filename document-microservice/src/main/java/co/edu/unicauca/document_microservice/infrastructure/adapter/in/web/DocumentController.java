package co.edu.unicauca.document_microservice.infrastructure.adapter.in.web;

import co.edu.unicauca.document_microservice.application.port.in.DeleteDocumentUseCase;
import co.edu.unicauca.document_microservice.application.port.in.UploadDocumentUseCase;
import co.edu.unicauca.document_microservice.application.port.in.GetDocumentUseCase;
import co.edu.unicauca.document_microservice.application.port.in.ListDocumentsUseCase;
import co.edu.unicauca.document_microservice.application.port.in.command.UploadDocumentCommand;
import co.edu.unicauca.document_microservice.domain.model.Document;
import co.edu.unicauca.document_microservice.infrastructure.adapter.in.web.dto.DocumentRequest;
import co.edu.unicauca.document_microservice.infrastructure.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/v1/documents")
public class DocumentController {

    private final UploadDocumentUseCase uploadDocumentUseCase;
    private final GetDocumentUseCase getDocumentUseCase;
    private final ListDocumentsUseCase listDocumentsUseCase;
    private final DeleteDocumentUseCase deleteDocumentUseCase;

    @Autowired
    public DocumentController(
            UploadDocumentUseCase uploadDocumentUseCase,
            GetDocumentUseCase getDocumentUseCase,
            ListDocumentsUseCase listDocumentsUseCase,
            DeleteDocumentUseCase deleteDocumentUseCase

    ) {
        this.uploadDocumentUseCase = uploadDocumentUseCase;
        this.getDocumentUseCase = getDocumentUseCase;
        this.listDocumentsUseCase = listDocumentsUseCase;
        this.deleteDocumentUseCase = deleteDocumentUseCase;
    }

    // --------------------------------------------------
    // 1. SUBIR DOCUMENTO (Usa UploadDocumentUseCase)
    // --------------------------------------------------
    @PostMapping
    public ResponseEntity<Document> uploadDocument(@ModelAttribute DocumentRequest request) throws Exception {
        Document savedDocument = uploadDocumentUseCase.uploadDocument(request);
        return new ResponseEntity<>(savedDocument, HttpStatus.CREATED);
    }

    // --------------------------------------------------
    // 2. OBTENER METADATOS POR ID (Usa GetDocumentUseCase)
    // --------------------------------------------------
    @GetMapping("/{id}")
    public ResponseEntity<Document> getDocumentById(@PathVariable Long id) {
        // Delega la responsabilidad de obtener metadatos al Caso de Uso de obtención.
        Document document = getDocumentUseCase.getDocumentById(id);
        return ResponseEntity.ok(document);
    }

    // --------------------------------------------------
    // 3. LISTAR POR PROYECTO (Usa ListDocumentsUseCase)
    // --------------------------------------------------
    @GetMapping("/project/{projectId}")
    public ResponseEntity<List<Document>> getDocumentsByProject(@PathVariable Long projectId) {
        // Delega la responsabilidad de listar al Caso de Uso de listado.
        List<Document> documents = listDocumentsUseCase.listByProject(projectId);
        return ResponseEntity.ok(documents);
    }

    // --------------------------------------------------
    // 4. DESCARGAR DOCUMENTO (Usa GetDocumentUseCase)
    // --------------------------------------------------
    @GetMapping("/{id}/download")
    public ResponseEntity<byte[]> downloadDocument(@PathVariable Long id) throws Exception {
        // Delega la responsabilidad de obtener los bytes al Caso de Uso de obtención.
        byte[] fileBytes = getDocumentUseCase.getFileBytes(id);

        // Retorna los bytes del archivo con el tipo de contenido adecuado
        return ResponseEntity.ok()
                .header("Content-Disposition", "attachment; filename=\"documento_" + id + ".pdf\"")
                .body(fileBytes);
    }
    // --------------------------------------------------
    // 4. ELIMINAR DOCUMENTO (Usa DeleteDocumentUseCase)
    // --------------------------------------------------
    @DeleteMapping("/{id}") // <-- ESTO SOLUCIONA EL 405
    public ResponseEntity<Void> deleteDocument(@PathVariable("id") Long id) {
        try {
            deleteDocumentUseCase.deleteDocument(id);
            // 204 No Content es el estándar para DELETE exitoso sin cuerpo de respuesta
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            // Manejo de errores genéricos (e.g., error de DB o sistema de archivos)
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}