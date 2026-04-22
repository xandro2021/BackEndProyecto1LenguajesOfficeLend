package proyecto1.officelend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import proyecto1.officelend.entity.Prestamo;
import proyecto1.officelend.repository.PrestamoRepositorio;

@Service
public class PrestamoService {
    
     private PrestamoRepositorio prestamoRepositorio;

    public PrestamoService(PrestamoRepositorio prestamoRepositorio) {
        this.prestamoRepositorio = prestamoRepositorio;
    }

    public Prestamo registrarPrestamo(Prestamo prestamo) {
        return prestamoRepositorio.save(prestamo);
    }

    public List<Prestamo> obtenerPrestamos() {
        return prestamoRepositorio.findAll();
    }

    public Optional<Prestamo> consultarPrestamo(int id) { 
        return prestamoRepositorio.findById(id);
    }

    public void eliminarPrestamo(int id) {
        prestamoRepositorio.deleteById(id);
    }

    public Prestamo actualizarPrestamo(int id, Prestamo prestamo) {
        Optional<Prestamo> existingPrestamo = prestamoRepositorio.findById(id);
        if (existingPrestamo.isPresent()) {
            Prestamo updatedPrestamo = existingPrestamo.get();
            updatedPrestamo.setFecha_solicitud(prestamo.getFecha_solicitud());
            updatedPrestamo.setFecha_inicio(prestamo.getFecha_inicio());
            updatedPrestamo.setFecha_fin_estimada(prestamo.getFecha_fin_estimada());
            updatedPrestamo.setFecha_devolucion_real(prestamo.getFecha_devolucion_real());
            return prestamoRepositorio.save(updatedPrestamo);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Prestamo no encontrado");
        }
    }
}
