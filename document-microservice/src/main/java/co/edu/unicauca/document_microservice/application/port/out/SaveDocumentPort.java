package co.edu.unicauca.document_microservice.application.port.out;

import co.edu.unicauca.document_microservice.domain.model.Document;

public interface SaveDocumentPort {
    /**
     * Guarda el Document (metadatos) y el archivo binario asociado (bytes).
     *
     * @param document  El objeto Document de dominio a guardar (viene con ID=null).
     * @param fileBytes El contenido binario del archivo.
     * @return El objeto Document de dominio con el ID asignado por la BD
     * y la URL final del archivo ya guardada.
     * @throws Exception Si ocurre un error durante la persistencia o el almacenamiento del archivo.
     */
    Document save(Document document, byte[] fileBytes) throws Exception;
}