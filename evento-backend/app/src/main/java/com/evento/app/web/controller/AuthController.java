package com.evento.app.web.controller;

import com.evento.app.web.dto.LoginResponseDto;
import com.evento.app.web.dto.UserLoginDto;
import com.evento.app.web.dto.UserRegistrationDto;
import com.evento.app.web.facade.AuthenticationValidationFacade;
import com.evento.app.web.service.AuthenticationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@Slf4j
public class AuthController {
    private final AuthenticationService authenticationService;
    private final AuthenticationValidationFacade authenticationValidationFacade;

    public AuthController(AuthenticationService authenticationService, AuthenticationValidationFacade authenticationValidationFacade) {
        this.authenticationService = authenticationService;
        this.authenticationValidationFacade = authenticationValidationFacade;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody UserRegistrationDto userRegistrationDto, BindingResult bindingResult) {
        authenticationValidationFacade.validateUserRegistration(userRegistrationDto, bindingResult);

        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
        }

        authenticationService.registerUser(userRegistrationDto);
        return ResponseEntity.status(HttpStatus.CREATED).body("User created successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody UserLoginDto userLoginDto, BindingResult bindingResult) {
        authenticationValidationFacade.validateUserLogin(userLoginDto, bindingResult);

        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
        }

        LoginResponseDto loginResponseDto = authenticationService.loginUser(userLoginDto);
        return ResponseEntity.ok(loginResponseDto);
    }
}
