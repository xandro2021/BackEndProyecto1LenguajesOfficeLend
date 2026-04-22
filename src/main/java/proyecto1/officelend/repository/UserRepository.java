package proyecto1.officelend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import proyecto1.officelend.entity.User;


@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsername(String username);

    void deleteById(Integer id);
}
