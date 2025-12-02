package co.edu.unicauca.document_microservice.infrastructure.adapter.out.storage;

public interface FileStorageService {
    String saveFile(byte[] fileBytes, String fileName) throws Exception;
    byte[] readFile(String fileUrl) throws Exception;
    void deleteFile(String fileUrl) throws Exception;
}
