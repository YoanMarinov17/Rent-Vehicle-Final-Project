package org.softuni.Rent_Vehicle_Company.service;

import org.softuni.Rent_Vehicle_Company.model.dto.ReservationDto;

import java.security.Principal;

public interface ReservationService {
    void createReservation(ReservationDto resDto, Principal principal, Long id);
}

