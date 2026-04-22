package proyecto1.officelend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import proyecto1.officelend.entity.Equipo;

public interface EquipoRepository extends JpaRepository<Equipo, Integer> {
    
}