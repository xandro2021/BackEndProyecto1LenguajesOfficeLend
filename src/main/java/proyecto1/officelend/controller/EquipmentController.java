package proyecto1.officelend.controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import proyecto1.officelend.entity.Equipment;
import proyecto1.officelend.service.EquipmentService;

@CrossOrigin(origins = "*")
@SecurityRequirement(name = "bearerAuth")
@Tag(name = "Equipment", description = "API for managing equipment")
@RestController
@RequestMapping("/equipment")
public class EquipmentController {

    @Autowired
    private EquipmentService equipmentService;

    @GetMapping
    @Operation(summary = "Get all equipment", description = "Returns a list of equipment")
    public List<Equipment> get() {
        return equipmentService.getEquipments();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get equipment by ID", description = "Searches for equipment by its ID")
    public Equipment getById(@PathVariable int id) {
        return equipmentService.getEquipmentById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    @Operation(summary = "Create new equipment", description = "Adds a new piece of equipment")
    public Equipment add(@RequestBody Equipment equipment) {
        return equipmentService.registerEquipment(equipment);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update equipment", description = "Modifies an existing piece of equipment")
    public Equipment update(@PathVariable int id, @RequestBody Equipment equipment) {
        return equipmentService.updateEquipment(id, equipment);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete equipment", description = "Removes a piece of equipment from the database")
    public void delete(@PathVariable int id) {
        equipmentService.deleteEquipment(id);
    }
}
