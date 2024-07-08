package org.softuni.Rent_Vehicle_Company.model.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.softuni.Rent_Vehicle_Company.model.enums.EngineEnum;
import org.softuni.Rent_Vehicle_Company.model.enums.TypeEnum;

@Entity
@Table(name = "vans")
@Getter
@Setter
@NoArgsConstructor

public class Van extends Vehicle{


    @Column(name = "number_seats")
    private Integer numberOfSeats;

    public Van(TypeEnum type, String model, int year, EngineEnum engine, Integer numberOfSeats) {
        super(TypeEnum.VAN, model, year, engine);
        this.numberOfSeats = numberOfSeats;
    }

    public Van(Integer numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }
}
