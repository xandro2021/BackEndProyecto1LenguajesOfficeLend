package proyecto1.officelend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import proyecto1.officelend.entity.Loan;
import proyecto1.officelend.entity.LoanStatus;

public interface LoanRepository extends JpaRepository<Loan, Integer> {
  List<Loan> findByUserId(int userId);
  boolean existsByEquipmentIdAndStatus(int equipmentId, LoanStatus status);
}
