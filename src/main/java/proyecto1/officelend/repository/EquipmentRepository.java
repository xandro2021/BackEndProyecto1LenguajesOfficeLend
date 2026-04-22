package proyecto1.officelend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import proyecto1.officelend.entity.Equipment;

public interface EquipmentRepository extends JpaRepository<Equipment, Integer> {
    
}