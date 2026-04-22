package proyecto1.officelend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import proyecto1.officelend.entity.Prestamo;

public interface PrestamoRepositorio extends JpaRepository<Prestamo, Integer> {
    
}
