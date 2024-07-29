package org.softuni.Rent_Vehicle_Company.model.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

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
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fromDate;


    @Column(name = "end_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;


    @Column(name = "pick_up_time")
    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime pickUpTime;

    @Column(name = "card_credentials")
    private String cardCredentials;

    @ManyToOne()
    @JoinColumn(name = "user_id")
    private User user;



    @ManyToOne()
    @JoinColumn(name = "vehicle_id")
    private Vehicle vehicle;


}



