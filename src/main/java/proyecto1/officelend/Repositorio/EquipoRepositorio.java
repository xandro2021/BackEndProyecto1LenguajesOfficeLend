package proyecto1.officelend.Repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import proyecto1.officelend.entity.Equipo;

public interface EquipoRepositorio extends JpaRepository<Equipo, Integer> {
    
}