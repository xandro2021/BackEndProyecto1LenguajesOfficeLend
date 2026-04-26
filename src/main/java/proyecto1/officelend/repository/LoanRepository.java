package proyecto1.officelend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import proyecto1.officelend.entity.Loan;
import proyecto1.officelend.entity.LoanStatus;

public interface LoanRepository extends JpaRepository<Loan, Integer> {
    boolean existsByEquipmentIdAndStatus(int equipmentId, LoanStatus status);
}
