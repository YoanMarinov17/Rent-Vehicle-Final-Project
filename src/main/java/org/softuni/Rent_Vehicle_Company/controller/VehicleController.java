package org.softuni.Rent_Vehicle_Company.controller;


import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.softuni.Rent_Vehicle_Company.model.dto.CarDto;
import org.softuni.Rent_Vehicle_Company.model.dto.TruckDto;
import org.softuni.Rent_Vehicle_Company.model.dto.VanDto;
import org.softuni.Rent_Vehicle_Company.model.dto.VehicleDto;
import org.softuni.Rent_Vehicle_Company.model.entity.User;
import org.softuni.Rent_Vehicle_Company.model.enums.EngineEnum;
import org.softuni.Rent_Vehicle_Company.model.enums.TypeCar;
import org.softuni.Rent_Vehicle_Company.model.enums.TypeEnum;
import org.softuni.Rent_Vehicle_Company.repository.UserRepository;
import org.softuni.Rent_Vehicle_Company.service.*;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.Optional;


@Controller
public class VehicleController {

    @ModelAttribute("allVehicleTypes")
    public TypeEnum[] vehicleTypes() {
        return TypeEnum.values();
    }


    @ModelAttribute("allEngineTypes")
    public EngineEnum[] allEngineTypes() {
        return EngineEnum.values();
    }

    private final VehicleService vehicleService;

    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;

    }


    @GetMapping("/my-vehicles")
    public String getAllVehicles(Principal principal, Model model){

        model.addAttribute("allVehicles",vehicleService.getAllVehiclesByUser(principal));



        return "my-vehicles";
    }

//
    @GetMapping("/vehicle-details/{id}")
    public String carDetails(@PathVariable("id") Long id,
                             Model model) throws ChangeSetPersister.NotFoundException {

        model.addAttribute("vehicleDetails", vehicleService.getVehicleDetails(id));

        return "vehicle-details";
    }


    @DeleteMapping("/vehicle-details/{id}")
    public String deleteCar(@PathVariable("id") Long id) {

        vehicleService.deleteOffer(id);

        return "redirect:/my-vehicles";
    }



}
