package org.softuni.Rent_Vehicle_Company.controller;


import jakarta.websocket.server.PathParam;
import org.softuni.Rent_Vehicle_Company.model.dto.ReservationDto;
import org.softuni.Rent_Vehicle_Company.service.ReservationService;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;


@Controller
@RequestMapping("my-requests")
public class RequestController {

    private final ReservationService reservationService;

    public RequestController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping("")
    public String getRequest(Model model, Principal principal){

        model.addAttribute("allReservations", reservationService.getAllPendingRequests(principal));
        return "my-requests";
    }




    @GetMapping("/reservationID")
    public String getApproveStatus(@PathParam("reservationID") Long reservationID, Model model, String status) {

        model.addAttribute("reservationID", reservationID);


        return "redirect:/my-requests";
    }


    @PostMapping("")
    public String createReservation(ReservationDto resDto, @PathParam("reservationID") Long reservationID) {



        reservationService.getRequestStatus(reservationID);


        //TODO:
        return "redirect:/cars";
    }



}
