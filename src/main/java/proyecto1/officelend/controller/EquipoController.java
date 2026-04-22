package proyecto1.officelend.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import proyecto1.officelend.entity.Equipo;
import proyecto1.officelend.service.EquipoService;

@CrossOrigin(origins = "*")
@Tag(name = "Equipos", description = "API para gestionar equipos")
@RestController 
@RequestMapping("/equipos")

public class EquipoController {
    @Autowired
    private EquipoService equipoService;

    @GetMapping
    @Operation(summary = "Obtener todos los equipos", description = "Devuelve una lista de equipos")
    public List<Equipo> get() {
        return equipoService.obtenerEquipos();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener un equipo por ID", description = "Busca un equipo por su ID")
    public Equipo getById(@PathVariable int id) {
        return equipoService.consultarEquipo(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    @Operation(summary = "Crear un nuevo equipo", description = "Agrega un nuevo equipo")
    public Equipo add(@RequestBody Equipo equipo) {
        return equipoService.registrarEquipo(equipo);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Modificar un equipo", description = "Modifica un equipo existente")
    public Equipo update(@PathVariable int id, @RequestBody Equipo equipo) {
        return equipoService.actualizarEquipo(id, equipo);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un equipo", description = "Elimina un equipo de la base de datos")
    public void delete(@PathVariable int id) {
        equipoService.eliminarEquipo(id);
    }
}
