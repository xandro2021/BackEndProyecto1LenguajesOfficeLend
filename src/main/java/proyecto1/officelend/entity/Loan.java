package proyecto1.officelend.entity;

import java.time.LocalDate;
import jakarta.persistence.*;

@Entity
@Table(name = "loans")
public class Loan {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column(name = "request_date", nullable = false)
  private LocalDate requestDate;

  @Column(name = "start_date", nullable = false)
  private LocalDate startDate;

  @Column(name = "estimated_end_date", nullable = false)
  private LocalDate estimatedEndDate;

  @Column(name = "actual_return_date")
  private LocalDate actualReturnDate;

  @Column(columnDefinition = "TEXT")
  private String justification;

  // Aprobado, Rechazado, Pendiente
  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private LoanStatus status;

  @ManyToOne
  @JoinColumn(name = "equipment_id")
  private Equipment equipment;

  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;

  public Loan() {
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public LocalDate getRequestDate() {
    return requestDate;
  }

  public void setRequestDate(LocalDate requestDate) {
    this.requestDate = requestDate;
  }

  public LocalDate getStartDate() {
    return startDate;
  }

  public void setStartDate(LocalDate startDate) {
    this.startDate = startDate;
  }

  public LocalDate getEstimatedEndDate() {
    return estimatedEndDate;
  }

  public void setEstimatedEndDate(LocalDate estimatedEndDate) {
    this.estimatedEndDate = estimatedEndDate;
  }

  public LocalDate getActualReturnDate() {
    return actualReturnDate;
  }

  public void setActualReturnDate(LocalDate actualReturnDate) {
    this.actualReturnDate = actualReturnDate;
  }

  public String getJustification() {
    return justification;
  }

  public void setJustification(String justification) {
    this.justification = justification;
  }

  public LoanStatus getStatus() {
    return status;
  }

  public void setStatus(LoanStatus status) {
    this.status = status;
  }

  public Equipment getEquipment() {
    return equipment;
  }

  public void setEquipment(Equipment equipment) {
    this.equipment = equipment;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  @Override
  public String toString() {
    return "Loan [id=" + id + ", requestDate=" + requestDate + ", startDate=" + startDate + ", estimatedEndDate="
        + estimatedEndDate + ", actualReturnDate=" + actualReturnDate + ", justification=" + justification + ", status="
        + status + ", equipment=" + equipment + ", user=" + user + "]";
  }

}
