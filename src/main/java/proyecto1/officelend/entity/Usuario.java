package proyecto1.officelend.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "usuario")
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

    // Constructores, getters y setters...
    public Usuario() {
    }

    public Usuario(String usuario, String contraseña, String correo, String rol, String nombreCompleto) {
        this.usuario = usuario;
        this.contraseña = contraseña;
        this.correo = correo;
        this.rol = rol;
        this.nombreCompleto = nombreCompleto;
    }

    public int getId() {
        return id;
    }

    public String getUsuario() {
        return usuario;
    }
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
    
    public String getContraseña() {
        return contraseña;
    }
    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getCorreo() {
        return correo;
    }
    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getRol() {
        return rol;
    }
    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }
    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public void setId(int id) {
        this.id = id;
    }

}
