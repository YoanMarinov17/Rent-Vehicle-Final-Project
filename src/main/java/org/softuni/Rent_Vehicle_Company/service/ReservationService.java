package org.softuni.Rent_Vehicle_Company.service;

import org.softuni.Rent_Vehicle_Company.model.dto.ReservationDto;
import org.softuni.Rent_Vehicle_Company.model.entity.Reservation;

import java.security.Principal;
import java.util.List;

public interface ReservationService {
    void createReservation(ReservationDto resDto, Principal principal, Long id);

    List<Reservation> getAllPendingRequests(Principal principal);

    void changeStatus(ReservationDto reservationDto, Principal principal);
}

