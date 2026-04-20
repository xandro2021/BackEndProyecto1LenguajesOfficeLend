package proyecto1.officelend.service;

import java.util.List;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import proyecto1.officelend.Repositorio.EquipoRepositorio;
import proyecto1.officelend.entity.Equipo;

@Service
public class EquipoService {
     private EquipoRepositorio equipoRepositorio;

    public EquipoService(EquipoRepositorio equipoRepositorio) {
        this.equipoRepositorio = equipoRepositorio;
    }

    public Equipo add(Equipo equipo) {
        return equipoRepositorio.save(equipo);
    }

    public List<Equipo> get() {
        return equipoRepositorio.findAll();
    }

    public Optional<Equipo> getById(int id) {
        return equipoRepositorio.findById(id);
    }

    public void delete(int id) {
        equipoRepositorio.deleteById(id);
    }

    public Equipo update(int id, Equipo equipo) {
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
