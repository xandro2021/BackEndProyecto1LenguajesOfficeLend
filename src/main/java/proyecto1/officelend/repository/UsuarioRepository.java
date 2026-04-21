package proyecto1.officelend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import proyecto1.officelend.entity.Usuario;


@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    Usuario findByUsuario(String usuario);

    void deleteById(Long id);
}
