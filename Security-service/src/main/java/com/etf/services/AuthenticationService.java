package com.etf.services;

import com.etf.dtos.LoginUserDto;
import com.etf.dtos.RegisterUserDto;
import com.etf.entities.User;
import com.etf.repositories.UserRepository;
import com.etf.responses.LoginResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationService {
    private final Logger logger = LoggerFactory.getLogger(AuthenticationService.class);

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    public AuthenticationService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    public User signup(RegisterUserDto input) {
        User user = new User();
                user.setUsername(input.getUsername());
                user.setEmail(input.getEmail());
                user.setPassword(passwordEncoder.encode(input.getPassword()));

        return userRepository.save(user);
    }

    /*public LoginResponse authenticate(LoginUserDto loginDto) {
        logger.info("Attempting authentication for username: {}", loginDto.getUsername());

        // Retrieve the user from the database
        Optional<User> optionalUser = userRepository.findByUsername(loginDto.getUsername());
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            // Verify the password
            if (passwordEncoder.matches(loginDto.getPassword(), user.getPassword())) {
                logger.info("Password verification succeeded for username: {}", loginDto.getUsername());
                // Generate JWT token
                String token = jwtService.generateToken(user);
                // Extract user roles
                //String role = user.getRole();
                // Return LoginResponse with token and user information
                return new LoginResponse(token, jwtService.getExpirationTime(), user.getUsername());
            } else {
                logger.warn("Password verification failed for username: {}", loginDto.getUsername());
            }
        } else {
            logger.warn("User not found for username: {}", loginDto.getUsername());
        }
        // Throw exception if authentication fails
        throw new RuntimeException("Invalid credentials");
    }*/

    public User authenticate(LoginUserDto input) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getUsername(),
                        input.getPassword()
                )
        );

        return userRepository.findByUsername(input.getUsername())
                .orElseThrow();
    }
}