package proyecto1.officelend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import proyecto1.officelend.entity.Usuario;
import proyecto1.officelend.repository.UsuarioRepository;

@Service

public class UsuarioService {
     private UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario registrarUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public List<Usuario> obtenerUsuarios() {
        return usuarioRepository.findAll();
    }

    public Optional<Usuario> consultarUsuario(int id) { 
        return usuarioRepository.findById(id);
    }

    public void eliminarUsuario(int id) {
        usuarioRepository.deleteById(id);
    }

    public Usuario actualizarUsuario(int id, Usuario usuario) {
        Optional<Usuario> existingUsuario = usuarioRepository.findById(id);
        if (existingUsuario.isPresent()) {
            Usuario updatedUsuario = existingUsuario.get();
            updatedUsuario.setUsuario(usuario.getUsuario());
            updatedUsuario.setContraseña(usuario.getContraseña());
            updatedUsuario.setCorreo(usuario.getCorreo());
            updatedUsuario.setRol(usuario.getRol());
            updatedUsuario.setNombreCompleto(usuario.getNombreCompleto());
            return usuarioRepository.save(updatedUsuario);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado");
        }
    }
}
