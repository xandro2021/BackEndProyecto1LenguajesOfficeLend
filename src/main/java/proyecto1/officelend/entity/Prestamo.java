package proyecto1.officelend.entity;

import java.sql.Date;

import jakarta.persistence.*;

@Entity
@Table(name = "prestamos")
public class Prestamo {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  @Column(name = "fecha_solicitud", nullable = false)
  private Date fecha_solicitud;
  @Column(name = "fecha_inicio", nullable = false)
  private Date fecha_inicio;
  @Column(name = "fecha_fin_estimada", nullable = false)
  private Date fecha_fin_estimada;
  @Column(name = "fecha_devolucion_real", nullable = false)
  private Date fecha_devolucion_real;
  @Column(name = "estado", nullable = false)
  private boolean estado;

  @ManyToOne
  @JoinColumn(name = "equipo_id")
  private Equipo equipo;

  @ManyToOne
  @JoinColumn(name = "usuario_id")
  private Usuario usuario;

  public Prestamo() {

  }

  public Prestamo(int id, Date fecha_solicitud, Date fecha_inicio, Date fecha_fin_estimada, Date fecha_devolucion_real,
      boolean estado, Equipo equipo, Usuario usuario) {
    this.id = id;
    this.fecha_solicitud = fecha_solicitud;
    this.fecha_inicio = fecha_inicio;
    this.fecha_fin_estimada = fecha_fin_estimada;
    this.fecha_devolucion_real = fecha_devolucion_real;
    this.estado = estado;
    this.equipo = equipo;
    this.usuario = usuario;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public Date getFecha_solicitud() {
    return fecha_solicitud;
  }

  public void setFecha_solicitud(Date fecha_solicitud) {
    this.fecha_solicitud = fecha_solicitud;
  }

  public Date getFecha_inicio() {
    return fecha_inicio;
  }

  public void setFecha_inicio(Date fecha_inicio) {
    this.fecha_inicio = fecha_inicio;
  }

  public Date getFecha_fin_estimada() {
    return fecha_fin_estimada;
  }

  public void setFecha_fin_estimada(Date fecha_fin_estimada) {
    this.fecha_fin_estimada = fecha_fin_estimada;
  }

  public Date getFecha_devolucion_real() {
    return fecha_devolucion_real;
  }

  public void setFecha_devolucion_real(Date fecha_devolucion_real) {
    this.fecha_devolucion_real = fecha_devolucion_real;
  }

  public boolean isEstado() {
    return estado;
  }

  public void setEstado(boolean estado) {
    this.estado = estado;
  }

  public Equipo getEquipo() {
    return equipo;
  }

  public void setEquipo(Equipo equipo) {
    this.equipo = equipo;
  }

  public Usuario getUsuario() {
    return usuario;
  }

  public void setUsuario(Usuario usuario) {
    this.usuario = usuario;
  }

}
