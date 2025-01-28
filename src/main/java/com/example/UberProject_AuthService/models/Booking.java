package com.example.UberProject_AuthService.models;


import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Setter
@Builder
@NoArgsConstructor
@Getter
@AllArgsConstructor
public class Booking extends BaseModel {


    @Enumerated(value = EnumType.STRING)
    private BookingStatus bookingStatus;

    @Temporal(value = TemporalType.DATE)
    private Date StartDate;

    @Temporal(value = TemporalType.DATE)
    private Date EndDate;

    private Long TotalDistanceTravelled;

    @ManyToOne(fetch = FetchType.LAZY)
    private Driver driver;

    @ManyToOne(fetch = FetchType.LAZY)
    private Passenger passenger;
}
