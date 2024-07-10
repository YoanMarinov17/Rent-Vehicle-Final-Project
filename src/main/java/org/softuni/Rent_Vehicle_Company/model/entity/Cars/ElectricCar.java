package org.softuni.Rent_Vehicle_Company.entity.Cars;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.softuni.Rent_Vehicle_Company.entity.enums.EngineEnum;
import org.softuni.Rent_Vehicle_Company.entity.enums.TypeCar;
import org.softuni.Rent_Vehicle_Company.entity.enums.TypeEnum;

@Getter
@Setter
@Entity
@Table(name = "electric_cars")
@NoArgsConstructor
public class ElectricCar extends Car{


    @Column(name = "battery_life_in_km")
    private int batteryLifeInKm;


    public ElectricCar(int batteryLifeInKm) {
        this.batteryLifeInKm = batteryLifeInKm;
    }

    public ElectricCar(TypeEnum type, String model, int year, EngineEnum engine, TypeCar car, int numberOfDoors, int numberOfSeats, int horsePower, int batteryLifeInKm) {
        super(type, model, year, engine, car, numberOfDoors, numberOfSeats, horsePower);
        this.batteryLifeInKm = batteryLifeInKm;
    }

    public ElectricCar(TypeCar car, int numberOfDoors, int numberOfSeats, int horsePower, int batteryLifeInKm) {
        super(car, numberOfDoors, numberOfSeats, horsePower);
        this.batteryLifeInKm = batteryLifeInKm;
    }
}
