package org.softuni.Rent_Vehicle_Company.model.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalTime;




@Getter
@Setter
@NoArgsConstructor
public class ReservationDto {

    @NotNull
    private String location;

    @NotNull
    private String fromDate;


    @NotNull
    @DateTimeFormat(pattern = "dd-MM-yyyy")

    private String endDate;


    @NotNull
    private String pickUpTime;

    @NotNull
    private String cardCredentials;
}
