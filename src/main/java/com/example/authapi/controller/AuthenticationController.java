package com.example.authapi.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.authapi.dtos.LoginUserDto;
import com.example.authapi.dtos.RegisterUserDto;
import com.example.authapi.entities.User;
import com.example.authapi.responses.LoginResponse;
import com.example.authapi.services.AuthenticationService;
import com.example.authapi.services.JwtService;

@RequestMapping("/auth")
@RestController
public class AuthenticationController {
    private final JwtService            jwtService;

    private final AuthenticationService authenticationService;

    public AuthenticationController(final JwtService jwtService, final AuthenticationService authenticationService) {
        this.jwtService            = jwtService;
        this.authenticationService = authenticationService;
    }

    @PostMapping("/signup")
    public ResponseEntity<User> register(@RequestBody final RegisterUserDto registerUserDto) {
        final var registeredUser = authenticationService.signup(registerUserDto);

        return ResponseEntity.ok(registeredUser);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody final LoginUserDto loginUserDto) {
        final var           authenticatedUser = authenticationService.authenticate(loginUserDto);

        final var           jwtToken          = jwtService.generateToken(authenticatedUser);

        final var loginResponse     = new LoginResponse();
        loginResponse.setToken(jwtToken);
        loginResponse.setExpiresIn(jwtService.getExpirationTime());

        return ResponseEntity.ok(loginResponse);
    }
}
