package proyecto1.officelend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import proyecto1.officelend.entity.Loan;

public interface LoanRepository extends JpaRepository<Loan, Integer> {
    
}
