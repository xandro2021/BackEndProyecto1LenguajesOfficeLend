package proyecto1.officelend.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "equipos")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Equipo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column( name = "nombre", nullable = false)
    private String nombre;
    @Column( name = "tipo", nullable = false)
    private String tipo;
    @Column( name = "descripcion", nullable = false)
    private String descripcion;
    @Column( name = "existencias", nullable = false)
    private int existencias;
    @Column( name = "disponible", nullable = false)
    private boolean disponible;
}
