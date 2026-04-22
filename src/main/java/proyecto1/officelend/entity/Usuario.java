package proyecto1.officelend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "usuarios")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

public class Usuario {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column(nullable = false, unique = true)
  private String usuario;

  @Column(nullable = false)
  private String contraseña;

  @Column(nullable = false, unique = true)
  private String correo;

  @Column(nullable = false)
  private String rol;

  @Column(name = "nombre_completo", nullable = false)
  private String nombreCompleto;

  
  
}
