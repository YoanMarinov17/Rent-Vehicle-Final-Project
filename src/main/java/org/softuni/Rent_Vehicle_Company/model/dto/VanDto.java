package org.softuni.Rent_Vehicle_Company.model.dto;


import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class VanDto  extends VehicleDto{

    @NotNull
    private Integer numberOfVanSeats;


    @NotNull
    private Integer pricePerDay;
}
