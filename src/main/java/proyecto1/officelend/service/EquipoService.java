package proyecto1.officelend.service;

import java.util.List;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import proyecto1.officelend.repository.EquipoRepository;
import proyecto1.officelend.entity.Equipo;

@Service
public class EquipoService {
     private EquipoRepository equipoRepositorio;

    public EquipoService(EquipoRepository equipoRepositorio) {
        this.equipoRepositorio = equipoRepositorio;
    }

    public Equipo registrarEquipo(Equipo equipo) {
        return equipoRepositorio.save(equipo);
    }

    public List<Equipo> obtenerEquipos() {
        return equipoRepositorio.findAll();
    }

    public Optional<Equipo> consultarEquipo(int id) { 
        return equipoRepositorio.findById(id);
    }

    public void eliminarEquipo(int id) {
        equipoRepositorio.deleteById(id);
    }

    public Equipo actualizarEquipo(int id, Equipo equipo) {
        Optional<Equipo> existingEquipo = equipoRepositorio.findById(id);
        if (existingEquipo.isPresent()) {
            Equipo updatedEquipo = existingEquipo.get();
            updatedEquipo.setNombre(equipo.getNombre());
             updatedEquipo.setTipo(equipo.getTipo());
            updatedEquipo.setDescripcion(equipo.getDescripcion());
             updatedEquipo.setExistencias(equipo.getExistencias());
              updatedEquipo.setEstado(equipo.isEstado());
            return equipoRepositorio.save(updatedEquipo);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Equipo no encontrado");
        }
    }
}
