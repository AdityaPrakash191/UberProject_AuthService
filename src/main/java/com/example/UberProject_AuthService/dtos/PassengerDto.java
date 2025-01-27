package com.example.UberProject_AuthService.dtos;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PassengerDto {

    private String id;

    private String name;

    private String password;

    private String email;

    private String phoneNumber;

    private Date createdAt;


}
