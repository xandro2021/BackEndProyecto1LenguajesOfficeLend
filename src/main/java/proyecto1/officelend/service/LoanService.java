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

    // reservo el equipo para evitar mas solicitudes por el mismo equipo
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
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "El prestamo no se ha encontrado"));

    // SOLO si cambia a DEVUELTO
    devolverEquipo(loan, existing);
    prestarEquipo(loan, existing);
    rechazarSolicitudEquipo(loan, existing);
    reevaluarSolicitudEquipo(loan, existing);

    // SOLO actualizar si viene valor (evita nulls)
    if (loan.getStatus() != null)
      existing.setStatus(loan.getStatus());

    return loanRepository.save(existing);
  }

  private void reevaluarSolicitudEquipo(Loan loan, Loan existing) {
    if (existing.getStatus() != LoanStatus.RECHAZADO && loan.getStatus() == LoanStatus.PENDIENTE) {
      // Al reevaluar vuelvo a reservar el equipo
      var equipment = existing.getEquipment();
      // verifico que haya stock sino paro todo con un error que recibe el cliente
      if (equipment.getStock()<=0) {
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No hay stock por lo que no se puede reevaluar la solicitud");
      }
      equipment.setStock(equipment.getStock() - 1);
      equipmentService.registerEquipment(equipment);

    }
  }

  private void rechazarSolicitudEquipo(Loan loan, Loan existing) {
    if (existing.getStatus() != LoanStatus.RECHAZADO && loan.getStatus() == LoanStatus.RECHAZADO) {

      // Devuelvo el articulo
      var equipment = existing.getEquipment();
      equipment.setStock(equipment.getStock() + 1);
      equipmentService.registerEquipment(equipment);

    }
  }

  private void prestarEquipo(Loan loan, Loan existing) {
    // Debe habersido anteriormente aprobado y la solicitud actual es de
    // prestado para concretar el prestamo al cliente. Es cuando el cliente
    // retira el articulo para su uso personal, es cuanto inicia el contador
    if (existing.getStatus() == LoanStatus.APROBADO && loan.getStatus() == LoanStatus.PRESTADO) {
      existing.setStartDate(LocalDate.now());
    }
  }

  private void devolverEquipo(Loan loan, Loan existing) {
    if (existing.getStatus() != LoanStatus.DEVUELTO
        && loan.getStatus() == LoanStatus.DEVUELTO) {

      var equipment = existing.getEquipment();
      equipment.setStock(equipment.getStock() + 1);
      equipmentService.registerEquipment(equipment);

      existing.setActualReturnDate(LocalDate.now());
    }
  }

}
