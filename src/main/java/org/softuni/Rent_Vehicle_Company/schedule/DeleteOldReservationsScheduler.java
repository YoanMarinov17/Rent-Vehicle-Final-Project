package org.softuni.Rent_Vehicle_Company.schedule;


import org.softuni.Rent_Vehicle_Company.model.entity.Reservation;
import org.softuni.Rent_Vehicle_Company.repository.ReservationRepository;
import org.softuni.Rent_Vehicle_Company.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Component
public class DeleteOldReservationsScheduler {


    private final ReservationService reservationService;

    private final ReservationRepository reservationRepository;

    public DeleteOldReservationsScheduler(ReservationService reservationService, ReservationRepository reservationRepository) {
        this.reservationService = reservationService;
        this.reservationRepository = reservationRepository;
    }



    //On every 5 days finished reservations will be deleted
    @Scheduled(cron = "0 0 0 */5 * ?")
    public void deleteOldReservations() {
        System.out.println("Running scheduled task to delete old reservations");

        List<Reservation> expiredReservations = reservationService.findExpiredReservations();

        reservationRepository.deleteAll(expiredReservations);

    }





    }

