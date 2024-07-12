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
import org.softuni.Rent_Vehicle_Company.service.UserService;
import org.softuni.Rent_Vehicle_Company.service.VehicleService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;


@Controller
public class VehicleController {

    @ModelAttribute("allVehicleTypes")
    public TypeEnum[] vehicleTypes() {
        return TypeEnum.values();
    }

    @ModelAttribute("allCarsType")
    public TypeCar[] allCarsTypes() {
        return TypeCar.values();
    }

    @ModelAttribute("allEngineTypes")
    public EngineEnum[] allEngineTypes() {
        return EngineEnum.values();
    }

    private final VehicleService vehicleService;


    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;


    }



    //Car
    @GetMapping("/add-car")
    public String getCar(Model model) {

        model.addAttribute("carDto", new CarDto());
        return "add-car";
    }

    @PostMapping("/add-car")
    public String createCar(CarDto carDto) {
        vehicleService.createCar(carDto);


        return "redirect:/index";
    }



    //Van
    @GetMapping("/add-van")
    public String getVan(Model model) {

        model.addAttribute("vanDto", new VanDto());
        return "add-van";
    }

    @PostMapping("/add-van")
    public String createVan(VanDto vanDto) {

        vehicleService.createVan(vanDto);
        return "redirect:/index";
    }





        //Truck
    @GetMapping("/add-truck")
    public String getVehicle(Model model) {

        model.addAttribute("truckDto", new TruckDto());
        return "add-truck";
    }

    @PostMapping("/add-truck")
    public String createTruck(TruckDto truckDto) {
vehicleService.createTruck(truckDto);

        return "redirect:/index";
    }




}
