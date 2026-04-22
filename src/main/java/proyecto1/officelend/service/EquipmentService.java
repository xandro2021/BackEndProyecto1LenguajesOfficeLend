package proyecto1.officelend.service;
import java.util.List;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import proyecto1.officelend.repository.EquipmentRepository;
import proyecto1.officelend.entity.Equipment;

@Service
public class EquipmentService {
    private EquipmentRepository equipmentRepository;

    public EquipmentService(EquipmentRepository equipmentRepository) {
        this.equipmentRepository = equipmentRepository;
    }

    public Equipment registerEquipment(Equipment equipment) {
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

    public Equipment updateEquipment(int id, Equipment equipment) {
        Optional<Equipment> existingEquipment = equipmentRepository.findById(id);
        if (existingEquipment.isPresent()) {
            Equipment updatedEquipment = existingEquipment.get();
            updatedEquipment.setName(equipment.getName());
            updatedEquipment.setType(equipment.getType());
            updatedEquipment.setDescription(equipment.getDescription());
            updatedEquipment.setStock(equipment.getStock());
            updatedEquipment.setStatus(equipment.isStatus());
            return equipmentRepository.save(updatedEquipment);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Equipment not found");
        }
    }
}
