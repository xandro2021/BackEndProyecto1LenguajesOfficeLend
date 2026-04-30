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

    if (loan.getRequestDate().isAfter(loan.getEstimatedEndDate())) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Rango de fechas invalido");
    }

    if (loan.getRequestDate().isBefore(LocalDate.now())) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La fecha de inicio no puede ser en el pasado");
    }

    if (loan.getEquipment() == null || loan.getEquipment().getId() == 0) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El equipo es requerido");
    }

    var equipment = equipmentService.getEquipmentById(loan.getEquipment().getId())
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Equipo no encontrado"));

    if (equipment.getStock() <= 0) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Sin stock");
    }

    // bajar stock, si llega a cero al guardarlo se aplica regla de negocio para
    // cambiar su estado a ocupado
    equipment.setStock(equipment.getStock() - 1);

    // En este caso se actualizaria el equipo en vez de agregar uno nuevo debido
    // al metodo save de repository, ademas de que hay validacion del stock sea
    // menor a 0 para tener estatus ocupado
    equipmentService.registerEquipment(equipment);

    loan.setStatus(LoanStatus.PENDIENTE);
    loan.setRequestDate(LocalDate.now());
    loan.setUser(userService.getCurrentUser());

    return loanRepository.save(loan);
  }

  public List<Loan> getLoans() {
    return loanRepository.findAll();
  }

  public List<Loan> getLoansByCurrentUser() {
    // Uso var para no importar la entidad
    var user = userService.getCurrentUser();
    return loanRepository.findByUserId(user.getId());
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

    // SOLO si cambia a DEVUELTO
    if (existing.getStatus() != LoanStatus.DEVUELTO
        && loan.getStatus() == LoanStatus.DEVUELTO) {

      var equipment = existing.getEquipment();
      equipment.setStock(equipment.getStock() + 1);
      equipmentService.registerEquipment(equipment);

      existing.setActualReturnDate(LocalDate.now());
    }

    // SOLO actualizar si viene valor (evita nulls)
    if (loan.getStartDate() != null)
      existing.setStartDate(loan.getStartDate());

    if (loan.getEstimatedEndDate() != null)
      existing.setEstimatedEndDate(loan.getEstimatedEndDate());

    if (loan.getJustification() != null)
      existing.setJustification(loan.getJustification());

    if (loan.getStatus() != null)
      existing.setStatus(loan.getStatus());

    return loanRepository.save(existing);
  }

}
