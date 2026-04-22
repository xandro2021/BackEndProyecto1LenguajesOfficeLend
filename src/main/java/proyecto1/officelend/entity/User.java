package proyecto1.officelend.entity;

import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column(nullable = false, unique = true)
  private String username;

  @Column(nullable = false)
  private String password;

  @Column(nullable = false, unique = true)
  private String email;

  @Column(nullable = false)
  private String role;

  @Column(name = "full_name", nullable = false)
  private String fullName;

  @OneToMany(mappedBy = "user")
  private List<Loan> loans;

  public User() {
  }

  public User(String username, String password, String email, String role, String fullName) {
    this.username = username;
    this.password = password;
    this.email = email;
    this.role = role;
    this.fullName = fullName;
  }

  public User(int id, String username, String password, String email,
      String role, String fullName, List<Loan> loans) {
    this.id = id;
    this.username = username;
    this.password = password;
    this.email = email;
    this.role = role;
    this.fullName = fullName;
    this.loans = loans;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getRole() {
    return role;
  }

  public void setRole(String role) {
    this.role = role;
  }

  public String getFullName() {
    return fullName;
  }

  public void setFullName(String fullName) {
    this.fullName = fullName;
  }

  public List<Loan> getLoans() {
    return loans;
  }

  public void setLoans(List<Loan> loans) {
    this.loans = loans;
  }

  @Override
  public String toString() {
    return "User [id=" + id + ", username=" + username + ", password=" + password + ", email=" + email + ", role="
        + role + ", fullName=" + fullName + ", loans=" + loans + "]";
  }

}
