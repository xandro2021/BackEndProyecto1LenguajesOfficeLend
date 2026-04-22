package proyecto1.officelend.service;
import java.util.List;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import proyecto1.officelend.entity.Loan;
import proyecto1.officelend.repository.LoanRepository;

@Service
public class LoanService {
    private LoanRepository loanRepository;

    public LoanService(LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }

    public Loan registerLoan(Loan loan) {
        return loanRepository.save(loan);
    }

    public List<Loan> getLoans() {
        return loanRepository.findAll();
    }

    public Optional<Loan> getLoanById(int id) {
        return loanRepository.findById(id);
    }

    public void deleteLoan(int id) {
        loanRepository.deleteById(id);
    }

    public Loan updateLoan(int id, Loan loan) {
        Optional<Loan> existingLoan = loanRepository.findById(id);
        if (existingLoan.isPresent()) {
            Loan updatedLoan = existingLoan.get();
            updatedLoan.setRequestDate(loan.getRequestDate());
            updatedLoan.setStartDate(loan.getStartDate());
            updatedLoan.setEstimatedEndDate(loan.getEstimatedEndDate());
            updatedLoan.setActualReturnDate(loan.getActualReturnDate());
            return loanRepository.save(updatedLoan);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Loan not found");
        }
    }
}
