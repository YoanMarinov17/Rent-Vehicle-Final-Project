package org.softuni.Rent_Vehicle_Company.model.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.softuni.Rent_Vehicle_Company.model.enums.EngineEnum;
import org.softuni.Rent_Vehicle_Company.model.enums.TypeCar;
import org.softuni.Rent_Vehicle_Company.model.enums.TypeEnum;

@Entity(name = "cars")
@Getter
@Setter
@NoArgsConstructor
public class Car extends Vehicle{

    @Column(name = "car_type")
    @Enumerated(value = EnumType.STRING)
    private TypeCar carType;


    @Column(name = "horse_power")
    private Integer horsePower;


    @Column(name = "number_doors")
    private  Integer numberOfDoors;


    @Column(name = "number_seats")
    private Integer numberOfSeats;

    @Column(name = "is_reserved")
    private Boolean isReserved;



    @Column(name = "price_per_day")
    private Integer pricePerDay;




}
