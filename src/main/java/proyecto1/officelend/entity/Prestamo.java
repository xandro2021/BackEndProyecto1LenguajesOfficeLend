package proyecto1.officelend.entity;

import java.sql.Date;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Entity
@Table(name = "prestamos")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Prestamo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "fecha_solicitud", nullable = false)
    private Date fecha_solicitud;
    @Column(name = "fecha_inicio",nullable = false)
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
}
