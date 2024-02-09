package com.example.demo.auth;


import com.example.demo.config.JwtService;
import com.example.demo.model.DBUser;
import com.example.demo.repository.DBUserRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AuthenticationService {
  private final DBUserRepository repository;
  private final PasswordEncoder passwordEncoder;
  private final JwtService jwtService;
  private final AuthenticationManager authenticationManager;

  public AuthenticationResponse register(RegisterRequest request) {
    DBUser user= new DBUser();
    user.setUsername(request.getUsername());
    user.setEmail(request.getEmail());
    user.setPassword(passwordEncoder.encode(request.getPassword()));
    user.setRole(request.getRole());
    repository.save(user);
    String jwt=jwtService.generateToken(user);
    return AuthenticationResponse.builder().Token(jwt).build();
  }

  public AuthenticationResponse login(AuthenticationRequest request) {
    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(
            request.getEmail(),
            request.getPassword()
        )
    );
    DBUser user=repository.findByEmail(request.getEmail())
            .orElseThrow(()-> new UsernameNotFoundException("User not found"));
    String jwt=jwtService.generateToken(user);
    return AuthenticationResponse.builder().Token(jwt).build();
  }

}
