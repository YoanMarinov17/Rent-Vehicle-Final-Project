package org.softuni.Rent_Vehicle_Company.model.dto;


import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TruckDto  extends VehicleDto{

    @NotNull
    private String upTones;

}
