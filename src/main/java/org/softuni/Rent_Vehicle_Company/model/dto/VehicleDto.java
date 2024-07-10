package org.softuni.Rent_Vehicle_Company.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.softuni.Rent_Vehicle_Company.model.enums.EngineEnum;
import org.softuni.Rent_Vehicle_Company.model.enums.TypeCar;
import org.softuni.Rent_Vehicle_Company.model.enums.TypeEnum;
import org.softuni.Rent_Vehicle_Company.model.enums.TypePremiumCar;


@NoArgsConstructor
@Getter
@Setter
public class VehicleDto {


    @NotNull
    private TypeEnum type;


    @NotBlank
    private String model;


    @NotNull
    private int year;


    @NotNull
    private EngineEnum engine;


}
