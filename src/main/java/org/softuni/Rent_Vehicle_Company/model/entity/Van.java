package org.softuni.Rent_Vehicle_Company.model.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
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
    private Integer numberOfVanSeats;


}
