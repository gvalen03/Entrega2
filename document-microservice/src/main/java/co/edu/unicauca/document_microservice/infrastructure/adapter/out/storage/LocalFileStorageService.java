package co.edu.unicauca.document_microservice.infrastructure.adapter.out.storage;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class LocalFileStorageService implements FileStorageService{

    // CRÍTICO: Eliminar la inicialización del campo final (final String uploadDir = "/opt/app/uploads/").
    // Al ser inyectado con @Value, no puede ser 'final' si lo inicializas aquí.
    @Value("${app.document.storage-dir:./documents-uploads}")
    private String storageDir;

    @Override
    public String saveFile(byte[] fileBytes, String fileName) throws Exception {

        // 1. Construir la ruta base con el valor de la propiedad
        Path uploadPath = Paths.get(storageDir);

        // 2. CRÍTICO: Crear el directorio si no existe
        if (Files.notExists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        // 3. Resolver la ruta completa del archivo
        Path targetLocation = uploadPath.resolve(fileName);

        // 4. Escribir los bytes en la ubicación
        Files.write(targetLocation, fileBytes);

        // Retorna la ruta completa (absoluta o relativa) que se guarda en la BD
        return targetLocation.toString();
    }

    /**
     * Lee el contenido binario de un archivo (byte[]) dado su ruta completa.
     * @param fileUrl La ruta completa del archivo en el sistema de archivos local.
     * @return Un array de bytes que representa el contenido del archivo.
     * @throws IOException si el archivo no existe o hay un error de lectura.
     */
    @Override
    public byte[] readFile(String fileUrl) throws IOException {

        if (fileUrl == null || fileUrl.trim().isEmpty()) {
            throw new IOException("La ruta del archivo es nula o vacía.");
        }

        Path filePath = Paths.get(fileUrl);

        // 1. Verificar si el archivo existe
        if (!Files.exists(filePath)) {
            throw new IOException("Archivo no encontrado en la ruta: " + fileUrl);
        }

        // 2. Leer todos los bytes del archivo
        return Files.readAllBytes(filePath);
    }

    /**
     * Elimina un archivo físico del sistema de archivos local.
     * @param fileUrl La ruta completa del archivo a eliminar.
     * @throws IOException si hay un error al intentar eliminar el archivo.
     */
    @Override
    public void deleteFile(String fileUrl) throws IOException {

        if (fileUrl == null || fileUrl.trim().isEmpty()) {
            return;
        }

        Path filePath = Paths.get(fileUrl);

        boolean deleted = Files.deleteIfExists(filePath);

        if (deleted) {
            System.out.println("Archivo de rollback eliminado con éxito: " + fileUrl);
        }
    }
}