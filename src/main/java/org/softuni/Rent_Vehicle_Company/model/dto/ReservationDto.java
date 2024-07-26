package org.softuni.Rent_Vehicle_Company.model.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;




@Getter
@Setter
@NoArgsConstructor
public class ReservationDto {

    @NotNull
    private String location;

    @NotNull
    private LocalDate fromDate;


    @NotNull
    private LocalDate endDate;


    @NotNull
    private LocalTime pickUpTime;

    @NotNull
    private String cardCredentials;
}
