package proyecto1.officelend.entity;

import java.sql.Date;

import jakarta.persistence.*;

@Entity
@Table(name = "loans")
public class Loan {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column(name = "request_date", nullable = false)
  private Date requestDate;

  @Column(name = "start_date", nullable = false)
  private Date startDate;

  @Column(name = "estimated_end_date", nullable = false)
  private Date estimatedEndDate;

  @Column(name = "actual_return_date", nullable = false)
  private Date actualReturnDate;

  @Column(nullable = false)
  private boolean status;

  @ManyToOne
  @JoinColumn(name = "equipment_id")
  private Equipment equipment;

  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;

  public Loan() {}

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public Date getRequestDate() {
    return requestDate;
  }

  public void setRequestDate(Date requestDate) {
    this.requestDate = requestDate;
  }

  public Date getStartDate() {
    return startDate;
  }

  public void setStartDate(Date startDate) {
    this.startDate = startDate;
  }

  public Date getEstimatedEndDate() {
    return estimatedEndDate;
  }

  public void setEstimatedEndDate(Date estimatedEndDate) {
    this.estimatedEndDate = estimatedEndDate;
  }

  public Date getActualReturnDate() {
    return actualReturnDate;
  }

  public void setActualReturnDate(Date actualReturnDate) {
    this.actualReturnDate = actualReturnDate;
  }

  public boolean isStatus() {
    return status;
  }

  public void setStatus(boolean status) {
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
        + estimatedEndDate + ", actualReturnDate=" + actualReturnDate + ", status=" + status + ", equipment="
        + equipment + ", user=" + user + "]";
  }

}
