package org.softuni.Rent_Vehicle_Company.model.entity.Cars;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.softuni.Rent_Vehicle_Company.model.entity.Vehicle;
import org.softuni.Rent_Vehicle_Company.model.enums.EngineEnum;
import org.softuni.Rent_Vehicle_Company.model.enums.TypeCar;
import org.softuni.Rent_Vehicle_Company.model.enums.TypeEnum;


@Entity
@Table(name = "cars")
@Getter
@Setter
@NoArgsConstructor
public class Car extends Vehicle {


    @Column(name = "car_type")
    @Enumerated(value = EnumType.STRING)
    private TypeCar carType;


    @Column(name = "horse_power")
    private int horsePower;


    @Column(name = "number_doors")
    private  int numberOfDoors;


    @Column(name = "number_seats")
    private int numberOfSeats;


    public Car(TypeEnum type, String model, int year, EngineEnum engine, TypeCar carType, int numberOfDoors, int numberOfSeats, int horsePower) {
        super(type, model, year, engine);
        this.carType = carType;
        this.horsePower = horsePower;
        this.numberOfDoors = numberOfDoors;
        this.numberOfSeats = numberOfSeats;
    }

    public Car(TypeCar carType, int numberOfDoors, int numberOfSeats, int horsePower) {
        this.carType = carType;
        this.horsePower = horsePower;
        this.numberOfDoors = numberOfDoors;
        this.numberOfSeats = numberOfSeats;
    }
}
