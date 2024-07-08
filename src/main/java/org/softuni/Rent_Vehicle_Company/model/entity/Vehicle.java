package org.softuni.Rent_Vehicle_Company.model.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.softuni.Rent_Vehicle_Company.model.enums.EngineEnum;
import org.softuni.Rent_Vehicle_Company.model.enums.TypeEnum;

@Entity
@Table(name = "vehicles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)

public abstract class Vehicle extends BaseEntity{


    @Column
    @Enumerated(value = EnumType.STRING)
    private TypeEnum type;


    @Column
    private String model;


    @Column
    private int year;


    @Column
    @Enumerated(value = EnumType.STRING)
    private EngineEnum engine;




}
