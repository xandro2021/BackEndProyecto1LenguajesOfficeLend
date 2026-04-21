package proyecto1.officelend.controller;
import proyecto1.officelend.entity.Usuario;
import proyecto1.officelend.repository.UsuarioRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")

public class UsuarioController {

    private final UsuarioRepository usuarioRepository;

    // Inyección de dependencias vía constructor
    public UsuarioController(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    // Listar todos los usuarios
    @GetMapping
    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }

    // Buscar usuario por ID
    @GetMapping("/{id}")
    public Usuario obtenerUsuario(@PathVariable Integer id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    // Crear nuevo usuario
    @PostMapping
    public Usuario crearUsuario(@RequestBody Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    // Actualizar usuario existente
    @PutMapping("/{id}")
    public Usuario actualizarUsuario(@PathVariable int id, @RequestBody Usuario usuario) {
        usuario.setId(id);
        return usuarioRepository.save(usuario);
    }

    // Eliminar usuario por ID
    @DeleteMapping("/{id}")
    public void eliminarUsuario(@PathVariable Integer id) {
        usuarioRepository.deleteById(id);
    }
    
}
