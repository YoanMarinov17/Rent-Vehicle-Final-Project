package org.softuni.Rent_Vehicle_Company.entity.Cars;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.softuni.Rent_Vehicle_Company.entity.Vehicle;
import org.softuni.Rent_Vehicle_Company.entity.enums.EngineEnum;
import org.softuni.Rent_Vehicle_Company.entity.enums.TypeCar;
import org.softuni.Rent_Vehicle_Company.entity.enums.TypeEnum;

import javax.print.attribute.standard.MediaSize;


@Entity
@Table(name = "cars")
@Getter
@Setter
@NoArgsConstructor
public class Car extends Vehicle {


    @Column(name = "car_type")
    @Enumerated(value = EnumType.STRING)
    private TypeCar car;


    @Column(name = "horse_power")
    private int horsePower;


    @Column(name = "number_doors")
    private  int numberOfDoors;


    @Column(name = "number_seats")
    private int numberOfSeats;


    public Car(TypeEnum type, String model, int year, EngineEnum engine, TypeCar car, int numberOfDoors, int numberOfSeats, int horsePower) {
        super(type, model, year, engine);
        this.car = car;
        this.horsePower = horsePower;
        this.numberOfDoors = numberOfDoors;
        this.numberOfSeats = numberOfSeats;
    }

    public Car(TypeCar car, int numberOfDoors, int numberOfSeats, int horsePower) {
        this.car = car;
        this.horsePower = horsePower;
        this.numberOfDoors = numberOfDoors;
        this.numberOfSeats = numberOfSeats;
    }
}
