package org.softuni.Rent_Vehicle_Company.controller;

import jakarta.servlet.http.HttpServletResponse;
import org.softuni.Rent_Vehicle_Company.model.entity.Reservation;
import org.softuni.Rent_Vehicle_Company.repository.ReservationRepository;
import org.softuni.Rent_Vehicle_Company.repository.UserRepository;
import org.softuni.Rent_Vehicle_Company.repository.VehicleRepository;
import org.softuni.Rent_Vehicle_Company.service.ReservationService;
import org.softuni.Rent_Vehicle_Company.service.UserService;
import org.softuni.Rent_Vehicle_Company.service.VehicleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin")

public class AdminController {

    private final UserRepository userRepository;
    private final VehicleRepository vehicleRepository;

    private final UserService userService;



    private final VehicleService vehicleService;

    public AdminController(UserRepository userRepository, VehicleRepository vehicleRepository, UserService userService, VehicleService vehicleService) {
        this.userRepository = userRepository;
        this.vehicleRepository = vehicleRepository;
        this.userService = userService;
        this.vehicleService = vehicleService;
    }


    @GetMapping("/all-users")
    public String getAllUsers(Model model){


        model.addAttribute("userInfo", userService.findAllRolesByUser());


        return "admin-all-users";
    }


    @GetMapping("/delete-user/{id}")
    public String getDeleteUser(Model model,@PathVariable("id") Long id){


        model.addAttribute("deleteUser", userService.getCurrentUser(id));

        return "delete-user";
    }


    @DeleteMapping("/delete-user/{id}")
    @ResponseStatus(value = HttpStatus.OK, reason = "User was deleted.")
    public String deleteUser(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        userService.deleteCurrentUser(id);

        return "redirect:/admin-all-users";

    }




}
