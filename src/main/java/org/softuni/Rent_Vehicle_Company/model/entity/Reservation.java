package org.softuni.Rent_Vehicle_Company.model.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;


@Entity(name = "reservations")
@Getter
@Setter
@NoArgsConstructor
public class Reservation  extends BaseEntity{


    @Column(name = "location")
    private String location;


    @Column(name = "from_date")
    private LocalDate fromDate;


    @Column(name = "end_date")
    private LocalDate endDate;


    @Column(name = "pick_up_time")
    private LocalTime pickUpTime;

    @Column(name = "card_credentials")
    private String cardCredentials;


    @OneToOne
    private Vehicle vehicle;



}



