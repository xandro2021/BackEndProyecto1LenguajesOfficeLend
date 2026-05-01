package proyecto1.officelend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import proyecto1.officelend.entity.User;
import proyecto1.officelend.repository.UserRepository;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@Service
public class UserService {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder; 

  public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
  }

  public User registerUser(User user) {

    if (userRepository.findByUsername(user.getUsername()).isPresent()) {
      throw new RuntimeException("El usuario ya existe");
    }

    user.setPassword(passwordEncoder.encode(user.getPassword()));

    user.setRole("ROLE_USER");

    return userRepository.save(user);
  }

  public List<User> getUsers() {
    return userRepository.findAll();
  }

  public Optional<User> getUserById(int id) {
    return userRepository.findById(id);
  }

  public void deleteUser(int id) {
    userRepository.deleteById(id);
  }

  public User updateUser(int id, User user) {
    Optional<User> existingUser = userRepository.findById(id);

    if (existingUser.isPresent()) {
      User updatedUser = existingUser.get();

      updatedUser.setUsername(user.getUsername());

      updatedUser.setPassword(passwordEncoder.encode(user.getPassword()));

      updatedUser.setEmail(user.getEmail());
      updatedUser.setRole(user.getRole());
      updatedUser.setFullName(user.getFullName());

      return userRepository.save(updatedUser);

    } else {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
    }
  }

  public User getCurrentUser() {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();

    if (auth == null || !auth.isAuthenticated()) {
      throw new RuntimeException("El usuario no esta autenticado");
    }

    String username = auth.getName();

    return userRepository.findByUsername(username)
        .orElseThrow(() -> new RuntimeException("El usuario no se ha encontrado"));
  }

}
