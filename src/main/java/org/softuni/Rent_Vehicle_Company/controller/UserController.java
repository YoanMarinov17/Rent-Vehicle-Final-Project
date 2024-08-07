package org.softuni.Rent_Vehicle_Company.controller;


import org.softuni.Rent_Vehicle_Company.model.entity.User;
import org.softuni.Rent_Vehicle_Company.repository.ReservationRepository;
import org.softuni.Rent_Vehicle_Company.repository.UserRepository;
import org.softuni.Rent_Vehicle_Company.service.ReservationService;
import org.softuni.Rent_Vehicle_Company.service.VehicleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.Optional;

@Controller
public class UserController {

    private final UserRepository userRepository;

    private final VehicleService vehicleService;

    private  final ReservationService reservationService;

    public UserController(UserRepository userRepository, VehicleService vehicleService, ReservationService reservationService) {
        this.userRepository = userRepository;
        this.vehicleService = vehicleService;
        this.reservationService = reservationService;
    }


    @GetMapping("/user-vehicles")
    public String getMyVehicles(Model model, Principal principal){
        String name = principal.getName();
        Optional<User> user = userRepository.findByUsername(name);

        if (user.isPresent()) {
            User userEntity = user.get();
            model.addAttribute("userVehicles",vehicleService.findAllByUserId(userEntity.getId()));

        }




        return "user-vehicles";
    }
}
