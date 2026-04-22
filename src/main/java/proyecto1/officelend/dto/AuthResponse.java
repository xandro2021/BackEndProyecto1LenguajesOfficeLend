package proyecto1.officelend.dto;

public class AuthResponse {

  private String token;
  private String role;

  public AuthResponse() {
  }

  public AuthResponse(String token, String role) {
    this.token = token;
    this.role = role;
  }

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }

  public String getRole() {
    return role;
  }

  public void setRole(String role) {
    this.role = role;
  }

  @Override
  public String toString() {
    return "AuthResponse [token=" + token + ", role=" + role + "]";
  }

}