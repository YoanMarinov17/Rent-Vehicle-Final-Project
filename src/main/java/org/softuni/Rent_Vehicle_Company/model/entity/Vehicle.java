package org.softuni.Rent_Vehicle_Company.model.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.softuni.Rent_Vehicle_Company.model.entity.enums.StatusEnum;
import org.softuni.Rent_Vehicle_Company.model.enums.EngineEnum;
import org.softuni.Rent_Vehicle_Company.model.enums.TypeEnum;

import java.util.List;

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
    private String brand;


    @Column
    private String model;


    @Column
    private int year;


    @Column
    @Enumerated(value = EnumType.STRING)
    private EngineEnum engine;




    @ManyToOne()
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "image_url")
    @Size(min = 5, max = 1232132132)
    private String imageUrl;

    @OneToMany(mappedBy = "vehicle")
    private List<Reservation> reservation;





}
