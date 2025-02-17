package org.softuni.Rent_Vehicle_Company.model.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.softuni.Rent_Vehicle_Company.model.enums.TypeCar;


@Getter
@Setter
@NoArgsConstructor
public class CarDto extends VehicleDto {


    @NotNull
    private TypeCar carType;

    @NotNull
    private Integer horsePower;


    @NotNull
    private Integer numberOfDoors;


    @NotNull
    private Integer numberOfSeats;

    @NotNull
    private Integer pricePerDay;


    @NotNull
    private String imageUrl;
}
