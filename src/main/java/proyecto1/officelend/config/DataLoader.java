package proyecto1.officelend.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import proyecto1.officelend.entity.User;
import proyecto1.officelend.repository.UserRepository;

@Configuration
public class DataLoader {
    @Bean
    CommandLineRunner initUsers(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            if (userRepository.findByUsername("admin").isEmpty()) {
                userRepository.save(new User(
                        "admin",
                        passwordEncoder.encode("admin"),
                        "admin@officelend.com",
                        "ROLE_ADMIN",
                        "Administrator"
                ));
            }
            if (userRepository.findByUsername("user").isEmpty()) {
                userRepository.save(new User(
                        "user",
                        passwordEncoder.encode("user"),
                        "user@officelend.com",
                        "ROLE_USER",
                        "Default User"
                ));
            }
        };
    }
}