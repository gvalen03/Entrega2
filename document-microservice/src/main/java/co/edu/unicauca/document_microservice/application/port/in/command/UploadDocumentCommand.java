package co.edu.unicauca.document_microservice.application.port.in.command;

public record UploadDocumentCommand(Long projectId, String documentType, String originalFileName, String extension,
                                    byte[] fileBytes, long size) {

}
