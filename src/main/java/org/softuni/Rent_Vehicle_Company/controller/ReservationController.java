package org.softuni.Rent_Vehicle_Company.controller;


import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.softuni.Rent_Vehicle_Company.model.dto.CarDto;
import org.softuni.Rent_Vehicle_Company.model.dto.ReservationDto;
import org.softuni.Rent_Vehicle_Company.service.CarService;
import org.softuni.Rent_Vehicle_Company.service.ReservationService;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;
    private final CarService carService;



    @GetMapping("/reservation")
    public String reservation(Model model, @PathParam("vehicleID") Long vehicleID) throws ChangeSetPersister.NotFoundException {

        model.addAttribute("reservationDto", new ReservationDto());
        model.addAttribute("vehicleID", vehicleID);


        return "reservation";
    }



    @PostMapping("/reservation")
    public String createReservation(ReservationDto resDto, Principal principal, @PathParam("vehicleID") Long vehicleID) {



        reservationService.createReservation(resDto, principal, vehicleID);


      //TODO:
        return "redirect:/cars";
    }
}
