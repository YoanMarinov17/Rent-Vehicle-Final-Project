package org.softuni.Rent_Vehicle_Company.repository;


import jakarta.transaction.Transactional;
import org.softuni.Rent_Vehicle_Company.model.entity.Reservation;
import org.softuni.Rent_Vehicle_Company.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    Optional<Reservation> findAllByUserId(Long id);


}
