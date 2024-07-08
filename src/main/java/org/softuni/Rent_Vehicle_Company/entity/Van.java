package org.softuni.Rent_Vehicle_Company.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.softuni.Rent_Vehicle_Company.entity.enums.EngineEnum;
import org.softuni.Rent_Vehicle_Company.entity.enums.TypeEnum;

@Entity
@Table(name = "vans")
@Getter
@Setter
@NoArgsConstructor

public class Van extends Vehicle{


    @Column(name = "number_seats")
    private int numberOfSeats;

    public Van(TypeEnum type, String model, int year, EngineEnum engine, int numberOfSeats) {
        super(TypeEnum.VAN, model, year, engine);
        this.numberOfSeats = numberOfSeats;
    }

    public Van(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }
}
