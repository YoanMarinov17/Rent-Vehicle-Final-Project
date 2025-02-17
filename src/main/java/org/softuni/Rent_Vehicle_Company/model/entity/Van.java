package org.softuni.Rent_Vehicle_Company.model.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
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

    @Column(name = "is_reserved")
    private Boolean isReserved;

    @Column(name = "price_per_day")
    private Integer pricePerDay;




}
