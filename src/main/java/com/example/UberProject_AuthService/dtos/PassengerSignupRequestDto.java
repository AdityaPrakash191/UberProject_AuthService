package com.example.UberProject_AuthService.dtos;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PassengerSignupRequestDto {

    private String name;

    private String password;

    private String email;

    private String phoneNumber;

}
