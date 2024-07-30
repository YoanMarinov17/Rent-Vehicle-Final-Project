package org.softuni.Rent_Vehicle_Company.service.impl;

import org.modelmapper.ModelMapper;
import org.softuni.Rent_Vehicle_Company.model.dto.ReservationDto;
import org.softuni.Rent_Vehicle_Company.model.entity.Reservation;
import org.softuni.Rent_Vehicle_Company.model.entity.User;
import org.softuni.Rent_Vehicle_Company.model.entity.Vehicle;
import org.softuni.Rent_Vehicle_Company.model.entity.enums.StatusEnum;
import org.softuni.Rent_Vehicle_Company.repository.ReservationRepository;
import org.softuni.Rent_Vehicle_Company.repository.UserRepository;
import org.softuni.Rent_Vehicle_Company.repository.VehicleRepository;
import org.softuni.Rent_Vehicle_Company.service.ReservationService;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class ReservationServiceImpl implements ReservationService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    private final VehicleRepository vehicleRepository;

    private final ReservationRepository reservationRepository;

    public ReservationServiceImpl(UserRepository userRepository, ModelMapper modelMapper, VehicleRepository vehicleRepository, ReservationRepository reservationRepository) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.vehicleRepository = vehicleRepository;
        this.reservationRepository = reservationRepository;
    }


    @Override
    public void createReservation(ReservationDto resDto, Principal principal, Long id) {


        Optional<Vehicle> optionalVehicle = vehicleRepository.findById(id);
        String name = principal.getName();
        Optional<User> optionalUser = userRepository.findByUsername(name);

        if (optionalVehicle.isPresent() && optionalUser.isPresent()) {
            Vehicle currentVehicle = optionalVehicle.get();

            Reservation res = modelMapper.map(resDto, Reservation.class);
            LocalDate fromDate = LocalDate.parse(resDto.getFromDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            LocalDate endDate = LocalDate.parse(resDto.getEndDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            LocalTime time = LocalTime.parse(resDto.getPickUpTime(), DateTimeFormatter.ofPattern("HH:mm"));
            res.setFromDate(fromDate);
            res.setEndDate(endDate);
            res.setPickUpTime(time);
            res.setVehicle(currentVehicle);
            res.setUser(optionalUser.get());
            res.setStatus(StatusEnum.PENDING);
            reservationRepository.save(res);
        }
    }

    @Override
    public List<Reservation> getAllPendingRequests(Principal principal) {

        String name = principal.getName();

        Optional<User> optionalUser = userRepository.findByUsername(name);

        List<Reservation> getAllReservationsForCurrentUser = new ArrayList<>();
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();

            getAllReservationsForCurrentUser = reservationRepository.findAll().stream().filter(r ->
                    r.getStatus() == StatusEnum.PENDING && r.getVehicle().getUser().getId() == user.getId()).toList();

        }

        return getAllReservationsForCurrentUser;
    }


    @Override
    public void changeStatus(ReservationDto reservationDto, Principal principal) {

    }
}
