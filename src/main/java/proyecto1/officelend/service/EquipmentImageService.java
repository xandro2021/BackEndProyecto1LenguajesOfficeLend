package proyecto1.officelend.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class EquipmentImageService {

  @Value("${equipment.upload.dir:uploads/equipment/}")
  private String uploadDir;

  /**
   * Guarda una imagen y retorna el nombre del archivo
   */
  public String saveImage(MultipartFile file) throws IOException {
    if (file.isEmpty()) {
      return null;
    }

    // Validar tipo de archivo
    String contentType = file.getContentType();
    if (!contentType.startsWith("image/")) {
      throw new IllegalArgumentException("El archivo debe ser una imagen");
    }

    // Generar nombre único
    String filename = UUID.randomUUID() + "_" + System.currentTimeMillis() + getExtension(file.getOriginalFilename());

    // Crear carpeta si no existe
    Path uploadPath = Paths.get(uploadDir);
    Files.createDirectories(uploadPath);

    // Guardar archivo
    Path filePath = uploadPath.resolve(filename);
    Files.write(filePath, file.getBytes());

    return filename;
  }

  /**
   * Elimina una imagen
   */
  public void deleteImage(String filename) {
    if (filename == null || filename.isEmpty()) {
      return;
    }
    try {
      Path filePath = Paths.get(uploadDir, filename);
      Files.deleteIfExists(filePath);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Obtiene la extensión del archivo
   */
  private String getExtension(String filename) {
    if (filename == null || !filename.contains(".")) {
      return ".jpg";
    }
    return filename.substring(filename.lastIndexOf("."));
  }
}