package proyecto1.officelend.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import proyecto1.officelend.entity.Loan;
import proyecto1.officelend.entity.LoanStatus;
import proyecto1.officelend.repository.LoanRepository;

@Service
public class LoanService {
  private final LoanRepository loanRepository;
  private final UserService userService;
  private final EquipmentService equipmentService;

  public LoanService(LoanRepository loanRepository, UserService userService, EquipmentService equipmentService) {
    this.loanRepository = loanRepository;
    this.userService = userService;
    this.equipmentService = equipmentService;
  }

  public Loan registerLoan(Loan loan) {

    if (loan.getStartDate().isAfter(loan.getEstimatedEndDate())) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Rango de fechas invalido");
    }

    if (loan.getStartDate().isBefore(LocalDate.now())) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La fecha de inicio no puede ser en el pasado");
    }

    if (loan.getEquipment() == null || loan.getEquipment().getId() == 0) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El equipo es requerido");
    }

    equipmentService.getEquipmentById(loan.getEquipment().getId())
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Equipo no encontrado"));

    loan.setStatus(LoanStatus.PENDIENTE);
    loan.setRequestDate(LocalDate.now());
    loan.setUser(userService.getCurrentUser());

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
    Loan existing = loanRepository.findById(id)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Loan not found"));

    existing.setStartDate(loan.getStartDate());
    existing.setEstimatedEndDate(loan.getEstimatedEndDate());
    existing.setActualReturnDate(loan.getActualReturnDate());

    existing.setJustification(loan.getJustification());
    existing.setStatus(loan.getStatus());

    return loanRepository.save(existing);
  }

}
