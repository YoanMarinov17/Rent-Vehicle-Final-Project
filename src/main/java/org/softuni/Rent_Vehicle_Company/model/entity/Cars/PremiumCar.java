package org.softuni.Rent_Vehicle_Company.model.entity.Cars;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.softuni.Rent_Vehicle_Company.model.enums.EngineEnum;
import org.softuni.Rent_Vehicle_Company.model.enums.TypeCar;
import org.softuni.Rent_Vehicle_Company.model.enums.TypeEnum;
import org.softuni.Rent_Vehicle_Company.model.enums.TypePremiumCar;

@Entity
@Table(name = "premium_cars")
@Getter
@Setter
@NoArgsConstructor
public class PremiumCar extends Car{


    @Column(name = "premium_car_type")
    @Enumerated(value = EnumType.STRING)
    private TypePremiumCar typePremiumCar;


    public PremiumCar(TypePremiumCar typePremiumCar) {
        this.typePremiumCar = typePremiumCar;
    }

    public PremiumCar(TypeEnum type, String model, int year, EngineEnum engine, TypeCar car, int numberOfDoors, int numberOfSeats, int horsePower, TypePremiumCar typePremiumCar) {
        super(type, model, year, engine, car, numberOfDoors, numberOfSeats, horsePower);
        this.typePremiumCar = typePremiumCar;
    }

    public PremiumCar(TypeCar car, int numberOfDoors, int numberOfSeats, int horsePower, TypePremiumCar typePremiumCar) {
        super(car, numberOfDoors, numberOfSeats, horsePower);
        this.typePremiumCar = typePremiumCar;
    }
}
