package com.example.UberProject_AuthService.controllers;


import com.example.UberProject_AuthService.dtos.PassengerDto;
import com.example.UberProject_AuthService.dtos.PassengerSignupRequestDto;
import com.example.UberProject_AuthService.services.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/auth")

public class AuthController {

    private AuthService authService;

    public AuthController(AuthService authService){
        this.authService = authService;
    }

    @PostMapping("/signup/passenger")
    public ResponseEntity<PassengerDto> signUp(@RequestBody PassengerSignupRequestDto passengerSignupRequestDto){
        PassengerDto response = this.authService.signupPassenger(passengerSignupRequestDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    @GetMapping("/signin/passenger")
    public ResponseEntity<?> signUp(){
        return new ResponseEntity<>(10, HttpStatus.OK);
    }
}
