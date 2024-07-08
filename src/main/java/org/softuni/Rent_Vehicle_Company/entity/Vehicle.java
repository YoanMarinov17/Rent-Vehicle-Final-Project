package org.softuni.Rent_Vehicle_Company.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.softuni.Rent_Vehicle_Company.entity.enums.EngineEnum;
import org.softuni.Rent_Vehicle_Company.entity.enums.TypeEnum;

@Entity
@Table(name = "vehicles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)

public class Vehicle extends BaseEntity{


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
