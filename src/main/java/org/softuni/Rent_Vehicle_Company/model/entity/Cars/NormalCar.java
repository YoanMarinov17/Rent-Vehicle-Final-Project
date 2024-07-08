package org.softuni.Rent_Vehicle_Company.model.entity.Cars;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.softuni.Rent_Vehicle_Company.model.enums.EngineEnum;
import org.softuni.Rent_Vehicle_Company.model.enums.TypeCar;
import org.softuni.Rent_Vehicle_Company.model.enums.TypeEnum;

@Entity
@Table(name = "normal_cars")
@Getter
@Setter
@NoArgsConstructor
public class NormalCar extends Car {


    @Column(name = "air_conditioner")
    private boolean airConditioner;

    public NormalCar(boolean airConditioner) {
        this.airConditioner = airConditioner;
    }

    public NormalCar(TypeEnum type, String model, int year, EngineEnum engine, TypeCar car, int numberOfDoors, int numberOfSeats, int horsePower, boolean airConditioner) {
        super(type, model, year, engine, car, numberOfDoors, numberOfSeats, horsePower);
        this.airConditioner = airConditioner;
    }

    public NormalCar(TypeCar car, int numberOfDoors, int numberOfSeats, int horsePower, boolean airConditioner) {
        super(car, numberOfDoors, numberOfSeats, horsePower);
        this.airConditioner = airConditioner;
    }

}
