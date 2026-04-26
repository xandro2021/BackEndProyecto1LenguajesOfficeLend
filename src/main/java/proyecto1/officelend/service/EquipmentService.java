package proyecto1.officelend.service;

import java.util.List;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import proyecto1.officelend.repository.EquipmentRepository;
import proyecto1.officelend.entity.Equipment;
import proyecto1.officelend.entity.EquipmentStatus;

@Service
public class EquipmentService {
  private EquipmentRepository equipmentRepository;

  public EquipmentService(EquipmentRepository equipmentRepository) {
    this.equipmentRepository = equipmentRepository;
  }

  public Equipment registerEquipment(Equipment equipment) {
    applyBusinessRules(equipment);
    return equipmentRepository.save(equipment);
  }

  public List<Equipment> getEquipments() {
    return equipmentRepository.findAll();
  }

  public Optional<Equipment> getEquipmentById(int id) {
    return equipmentRepository.findById(id);
  }

  public void deleteEquipment(int id) {
    equipmentRepository.deleteById(id);
  }

  public Equipment updateEquipment(int id, Equipment newData) {
    Equipment existing = equipmentRepository.findById(id)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Equipo no encontrado"));

    existing.setName(newData.getName());
    existing.setType(newData.getType());
    existing.setDescription(newData.getDescription());
    existing.setStock(newData.getStock());

    if (newData.getImageFilename() != null) {
      existing.setImageFilename(newData.getImageFilename());
    }

    existing.setStatus(newData.getStatus());
    applyBusinessRules(existing);

    return equipmentRepository.save(existing);
  }

  private void applyBusinessRules(Equipment equipment) {
    if (equipment.getStock() <= 0) {
      equipment.setStatus(EquipmentStatus.OCUPADO);
    } else if (equipment.getStatus() != EquipmentStatus.MANTENIMIENTO) {
      equipment.setStatus(EquipmentStatus.DISPONIBLE);
    }
  }
}
