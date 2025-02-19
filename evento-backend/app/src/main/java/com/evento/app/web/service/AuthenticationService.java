package com.evento.app.web.service;

import com.evento.app.application.exceptions.UserNotFoundException;
import com.evento.app.application.service.UserService;
import com.evento.app.domain.entity.User;
import com.evento.app.domain.repository.UserRepository;
import com.evento.app.infrastructure.security.service.JwtService;
import com.evento.app.web.dto.LoginResponseDto;
import com.evento.app.web.dto.UserLoginDto;
import com.evento.app.web.dto.UserRegistrationDto;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    private final JwtService jwtService;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    public AuthenticationService(
            JwtService jwtService,
            UserService userService,
            PasswordEncoder passwordEncoder,
            AuthenticationManager authenticationManager, UserRepository userRepository
    ) {
        this.jwtService = jwtService;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
    }


    public void registerUser(UserRegistrationDto userRegistrationDto) {
        final User user = toUser(userRegistrationDto);
        userService.registerUser(user);
    }

    public User toUser(UserRegistrationDto userRegistrationDto) {
        return new User(
                userRegistrationDto.getUsername(),
                passwordEncoder.encode(userRegistrationDto.getPassword()),
                userRegistrationDto.getEmail(),
                userRegistrationDto.getFullName()
        );
    }

    private User authenticate(UserLoginDto userLoginDto) {
        User user = userRepository.findByEmail(userLoginDto.getEmail())
                .orElseThrow(() -> new UserNotFoundException("User not found"));
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                user.getUsername(),
                userLoginDto.getPassword()
        ));
        return user;
    }

    public LoginResponseDto loginUser(UserLoginDto userLoginDto) {
        User authenticatedUser = authenticate(userLoginDto);

        String jwtToken = jwtService.generateToken(authenticatedUser);

        return new LoginResponseDto(jwtToken, jwtService.getExpirationTime());
    }
}
