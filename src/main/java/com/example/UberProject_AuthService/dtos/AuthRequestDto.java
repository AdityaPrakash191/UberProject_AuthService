package com.example.UberProject_AuthService.dtos;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthRequestDto {

    private String email;

    private String password;


}
