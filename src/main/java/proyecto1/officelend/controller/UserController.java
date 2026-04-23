package proyecto1.officelend.controller;
import proyecto1.officelend.entity.User;
import proyecto1.officelend.repository.UserRepository;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@CrossOrigin(origins = "*")
@SecurityRequirement(name = "bearerAuth")
@Tag(name = "Users", description = "API for managing users")
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserRepository userRepository;

    // Dependency injection via constructor
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // List all users
    @GetMapping
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    // Find user by ID
    @GetMapping("/{id}")
    public User getUserById(@PathVariable Integer id) {
        return userRepository.findById(id).orElse(null);
    }

    // Create new user
    @PostMapping
    public User createUser(@RequestBody User user) {
        return userRepository.save(user);
    }

    // Update existing user
    @PutMapping("/{id}")
    public User updateUser(@PathVariable int id, @RequestBody User user) {
        user.setId(id);
        return userRepository.save(user);
    }

    // Delete user by ID
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Integer id) {
        userRepository.deleteById(id);
    }
}
