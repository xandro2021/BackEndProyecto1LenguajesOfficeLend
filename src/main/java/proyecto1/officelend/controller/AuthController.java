package proyecto1.officelend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import proyecto1.officelend.dto.AuthResponse;
import proyecto1.officelend.dto.LoginRequest;
import proyecto1.officelend.security.JwtUtil;

@RestController
@RequestMapping("/auth")
public class AuthController {

  private final AuthenticationManager authenticationManager;
  private final UserDetailsService userDetailsService;
  private final JwtUtil jwtUtil;

  public AuthController(AuthenticationManager authenticationManager,
      UserDetailsService userDetailsService,
      JwtUtil jwtUtil) {
    this.authenticationManager = authenticationManager;
    this.userDetailsService = userDetailsService;
    this.jwtUtil = jwtUtil;
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

      // Consigo el role desde authorities
      String role = userDetails.getAuthorities().stream()
          .findFirst()
          .map(a -> a.getAuthority())
          .orElse("ROLE_USER");

      return ResponseEntity.ok(new AuthResponse(token, role)); // incluyo el role

    } catch (BadCredentialsException e) {

      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales inválidas");

    }
  }
}