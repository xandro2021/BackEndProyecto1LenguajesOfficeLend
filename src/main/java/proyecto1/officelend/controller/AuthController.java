package proyecto1.officelend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletRequest;
import proyecto1.officelend.dto.AuthResponse;
import proyecto1.officelend.dto.LoginRequest;
import proyecto1.officelend.entity.User;
import proyecto1.officelend.repository.UserRepository;
import proyecto1.officelend.security.JwtUtil;
import proyecto1.officelend.service.TokenBlacklistService;

@RestController
@RequestMapping("/auth")
public class AuthController {

  private final AuthenticationManager authenticationManager;
  private final UserDetailsService userDetailsService;
  private final JwtUtil jwtUtil;
  private final TokenBlacklistService blacklistService;
  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  public AuthController(
      AuthenticationManager authenticationManager,
      UserDetailsService userDetailsService,
      JwtUtil jwtUtil,
      TokenBlacklistService blacklistService,
      UserRepository userRepository,
      PasswordEncoder passwordEncoder) {

    this.authenticationManager = authenticationManager;
    this.userDetailsService = userDetailsService;
    this.jwtUtil = jwtUtil;
    this.blacklistService = blacklistService;
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
  }

  @PostMapping("/login")
  public ResponseEntity<?> login(@RequestBody LoginRequest request) {
    try {
      authenticationManager.authenticate(
          new UsernamePasswordAuthenticationToken(
              request.getUsername(),
              request.getPassword()));

      UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
      String token = jwtUtil.generateToken(userDetails);


      String role = userDetails.getAuthorities().stream()
          .findFirst()
          .map(a -> a.getAuthority())
          .orElse("ROLE_USER");

      return ResponseEntity.ok(new AuthResponse(token, role));

    } catch (BadCredentialsException e) {

      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales inválidas");

    }
  }

  @PostMapping("/logout")
  public ResponseEntity<?> logout(HttpServletRequest request) {
    String authHeader = request.getHeader("Authorization");

    if (authHeader != null && authHeader.startsWith("Bearer ")) {
      String token = authHeader.substring(7);
      blacklistService.blacklistToken(token);
    }

    return ResponseEntity.ok("Logout exitoso");
  }

  @PostMapping("/register")
  public ResponseEntity<?> register(@RequestBody User user) {

    if (userRepository.findByUsername(user.getUsername()).isPresent()) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST)
          .body("El usuario ya existe");
    }

    user.setRole("ROLE_USER");

    user.setPassword(passwordEncoder.encode(user.getPassword()));

    userRepository.save(user);

    return ResponseEntity.ok("Usuario registrado correctamente");
  }
}