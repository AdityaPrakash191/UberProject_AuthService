package com.example.UberProject_AuthService.controllers;


import com.example.UberProject_AuthService.dtos.AuthRequestDto;
import com.example.UberProject_AuthService.dtos.PassengerDto;
import com.example.UberProject_AuthService.dtos.PassengerSignupRequestDto;
import com.example.UberProject_AuthService.services.AuthService;

import com.example.UberProject_AuthService.services.JWTService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/auth")

public class AuthController {

    @Value("${cookie.expiry}")
    private int cookieExpiry;

    private final AuthService authService;

    private final AuthenticationManager authenticationManager;

    private final JWTService jwtService;

    public AuthController(AuthService authService,AuthenticationManager authenticationManager,JWTService jwtService){
        this.authService = authService;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;

    }

    @PostMapping("/signup/passenger")
    public ResponseEntity<PassengerDto> signUp(@RequestBody PassengerSignupRequestDto passengerSignupRequestDto){
        PassengerDto response = this.authService.signupPassenger(passengerSignupRequestDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    @PostMapping("/signin/passenger")
    public ResponseEntity<?> signUp(@RequestBody AuthRequestDto requestDto, HttpServletResponse response){

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(requestDto.getEmail(),requestDto.getPassword()));
        if(authentication.isAuthenticated()){
            String token = jwtService.createToken(requestDto.getEmail());
            ResponseCookie cookie = ResponseCookie.from("jwtToken",token)
                    .httpOnly(true)
                    .maxAge(cookieExpiry)
                    .secure(false) //tells we are using http not https
                    .build();
            response.setHeader(HttpHeaders.SET_COOKIE,cookie.toString());
            return new ResponseEntity<>(token,HttpStatus.OK);
        }else{
            return new ResponseEntity<>("Auth UnSuccessful",HttpStatus.OK);
        }
    }
}
