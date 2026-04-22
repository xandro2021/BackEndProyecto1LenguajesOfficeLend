package proyecto1.officelend.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "equipos")

public class Equipo {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  @Column(name = "nombre", nullable = false)
  private String nombre;
  @Column(name = "tipo", nullable = false)
  private String tipo;
  @Column(name = "descripcion", nullable = false)
  private String descripcion;
  @Column(name = "existencias", nullable = false)
  private int existencias;
  @Column(name = "estado", nullable = false)
  private boolean estado;

  @OneToMany(mappedBy = "equipo")
  private List<Prestamo> prestamos = new ArrayList<>();

  public Equipo() {
  }

  public Equipo(int id, String nombre, String tipo, String descripcion, int existencias, boolean estado) {
    this.id = id;
    this.nombre = nombre;
    this.tipo = tipo;
    this.descripcion = descripcion;
    this.existencias = existencias;
    this.estado = estado;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public String getTipo() {
    return tipo;
  }

  public void setTipo(String tipo) {
    this.tipo = tipo;
  }

  public String getDescripcion() {
    return descripcion;
  }

  public void setDescripcion(String descripcion) {
    this.descripcion = descripcion;
  }

  public int getExistencias() {
    return existencias;
  }

  public void setExistencias(int existencias) {
    this.existencias = existencias;
  }

  public boolean isEstado() {
    return estado;
  }

  public void setEstado(boolean estado) {
    this.estado = estado;
  }

  public List<Prestamo> getPrestamos() {
    return prestamos;
  }

  public void setPrestamos(List<Prestamo> prestamos) {
    this.prestamos = prestamos;
  }

  @Override
  public String toString() {
    return "Equipo [id=" + id + ", nombre=" + nombre + ", tipo=" + tipo + ", descripcion=" + descripcion
        + ", existencias=" + existencias + ", estado=" + estado + ", prestamos=" + prestamos + "]";
  }

}
