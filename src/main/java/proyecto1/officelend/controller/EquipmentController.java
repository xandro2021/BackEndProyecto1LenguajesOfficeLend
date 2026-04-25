package proyecto1.officelend.controller;

import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.access.prepost.PreAuthorize;
import proyecto1.officelend.entity.Equipment;
import proyecto1.officelend.entity.EquipmentStatus;
import proyecto1.officelend.service.EquipmentService;
import proyecto1.officelend.service.EquipmentImageService;

@CrossOrigin(origins = "*")
@SecurityRequirement(name = "bearerAuth")
@Tag(name = "Equipment", description = "API for managing equipment")
@RestController
@RequestMapping("/equipment")
public class EquipmentController {

  @Autowired
  private EquipmentService equipmentService;

  @Autowired
  private EquipmentImageService imageService;

  @GetMapping
  @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
  @Operation(summary = "Get all equipment")
  public List<Equipment> get() {
    return equipmentService.getEquipments();
  }

  @GetMapping("/{id}")
  @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
  @Operation(summary = "Get equipment by ID")
  public Equipment getById(@PathVariable int id) {
    return equipmentService.getEquipmentById(id)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
  }

  @PostMapping
  @PreAuthorize("hasRole('ADMIN')")
  @Operation(summary = "Create new equipment")
  public Equipment add(
      @RequestParam String name,
      @RequestParam String type,
      @RequestParam String description,
      @RequestParam int stock,
      @RequestParam String status,
      @RequestParam(required = false) MultipartFile image) throws IOException {

    Equipment equipment = new Equipment();
    equipment.setName(name);
    equipment.setType(type);
    equipment.setDescription(description);
    equipment.setStock(stock);
    // equipment.setStatus(Enum.valueOf(EquipmentStatus.class, status));
    equipment.setStatus(EquipmentStatus.valueOf(status.toUpperCase()));
    // Guardar imagen si se proporciona
    if (image != null && !image.isEmpty()) {
      String filename = imageService.saveImage(image);
      equipment.setImageFilename(filename);
    }

    return equipmentService.registerEquipment(equipment);
  }

  @PutMapping("/{id}")
  @PreAuthorize("hasRole('ADMIN')")
  @Operation(summary = "Update equipment")
  public Equipment update(
      @PathVariable int id,
      @RequestParam String name,
      @RequestParam String type,
      @RequestParam String description,
      @RequestParam int stock,
      @RequestParam String status,
      @RequestParam(required = false) MultipartFile image) throws IOException {

    Equipment equipment = equipmentService.getEquipmentById(id)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

    equipment.setName(name);
    equipment.setType(type);
    equipment.setDescription(description);
    equipment.setStock(stock);
    // equipment.setStatus(Enum.valueOf(EquipmentStatus.class, status));
    equipment.setStatus(EquipmentStatus.valueOf(status.toUpperCase()));

    // Si hay imagen nueva, eliminar la antigua y guardar la nueva
    if (image != null && !image.isEmpty()) {
      if (equipment.getImageFilename() != null) {
        imageService.deleteImage(equipment.getImageFilename());
      }
      String filename = imageService.saveImage(image);
      equipment.setImageFilename(filename);
    }

    return equipmentService.updateEquipment(id, equipment);
  }

  @DeleteMapping("/{id}")
  @PreAuthorize("hasRole('ADMIN')")
  @Operation(summary = "Delete equipment")
  public void delete(@PathVariable int id) {
    Equipment equipment = equipmentService.getEquipmentById(id)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

    // Eliminar imagen si existe
    if (equipment.getImageFilename() != null) {
      imageService.deleteImage(equipment.getImageFilename());
    }

    equipmentService.deleteEquipment(id);
  }
}
