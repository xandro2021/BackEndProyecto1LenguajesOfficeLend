package proyecto1.officelend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import proyecto1.officelend.entity.Prestamo;
import proyecto1.officelend.service.PrestamoService;


@CrossOrigin(origins = "*")
@Tag(name = "Prestamos", description = "API para gestionar prestamos")
@RestController 
@RequestMapping("/prestamos")

public class PrestamoController {
     @Autowired
    private PrestamoService prestamoService;

    @GetMapping
    @Operation(summary = "Obtener todos los prestamos", description = "Devuelve una lista de prestamos")
    public List<Prestamo> get() {
        return prestamoService.obtenerPrestamos();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener un prestamo por ID", description = "Busca un prestamo por su ID")
    public Prestamo getById(@PathVariable int id) {
        return prestamoService.consultarPrestamo(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    @Operation(summary = "Crear un nuevo prestamo", description = "Agrega un nuevo prestamo")
    public Prestamo add(@RequestBody Prestamo prestamo) {
        return prestamoService.registrarPrestamo(prestamo);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Modificar un prestamo", description = "Modifica un prestamo existente")
    public Prestamo update(@PathVariable int id, @RequestBody Prestamo prestamo) {
        return prestamoService.actualizarPrestamo(id, prestamo);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un prestamo", description = "Elimina un prestamo de la base de datos")
    public void delete(@PathVariable int id) {
        prestamoService.eliminarPrestamo(id);
    }
}
