package com.example.UberProject_AuthService.dtos;

import com.example.UberProject_AuthService.models.Passenger;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PassengerDto {

    private Long id;

    private String name;

    private String password;

    private String email;

    private String phoneNumber;

    private Date createdAt;

    public static PassengerDto from(Passenger passenger){
        PassengerDto passengerDto = PassengerDto.builder()
                .id(passenger.getId())
                .name(passenger.getName())
                .email(passenger.getEmail())
                .password(passenger.getPassword())
                .phoneNumber(passenger.getPhone_number())
                .createdAt(passenger.getCreatedAt())
                .build();
         return passengerDto;
    }


}
