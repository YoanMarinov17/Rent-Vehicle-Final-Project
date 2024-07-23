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

    @ModelAttribute("allCarsType")
    public TypeCar[] allCarsTypes() {
        return TypeCar.values();
    }

    @ModelAttribute("allEngineTypes")
    public EngineEnum[] allEngineTypes() {
        return EngineEnum.values();
    }

    private final VehicleService vehicleService;
    private final UserRepository userRepository;

    private final CarService carService;

    private final VanService vanService;

    private final TruckService truckService;


    public VehicleController(VehicleService vehicleService, UserRepository userRepository, CarService carService, VanService vanService, TruckService truckService) {
        this.vehicleService = vehicleService;

        this.userRepository = userRepository;
        this.carService = carService;
        this.vanService = vanService;

        this.truckService = truckService;
    }


    //Car
    @GetMapping("/add-car")
    public String getCar(Model model) {

        model.addAttribute("carDto", new CarDto());
        return "add-car";
    }

    @PostMapping("/add-car")
    public String createCar(CarDto carDto, Principal principal) {

        vehicleService.createCar(carDto, principal);


        return "redirect:/cars";
    }






    //Van
    @GetMapping("/add-van")
    public String getVan(Model model) {

        model.addAttribute("vanDto", new VanDto());
        return "add-van";
    }

    @PostMapping("/add-van")
    public String createVan(VanDto vanDto, Principal principal) {

        vehicleService.createVan(vanDto,principal);
        return "redirect:/vans";
    }








    //Truck
    @GetMapping("/add-truck")
    public String getVehicle(Model model) {

        model.addAttribute("truckDto", new TruckDto());
        return "add-truck";
    }

    @PostMapping("/add-truck")
    public String createTruck(TruckDto truckDto, Principal principal) {

        vehicleService.createTruck(truckDto, principal);

        return "redirect:/trucks";
    }




    @GetMapping("/cars")
    public String getAllCars(Model model) {

        model.addAttribute("allCars", carService.getAllCarSummary());
        return "cars";
    }
    @GetMapping("/vans")
    public String getAllVans(Model model) {

        model.addAttribute("allVans", vanService.getAllVansSummary());
        return "vans";
    }
    @GetMapping("/trucks")
    public String getAllTrucks(Model model) {

        model.addAttribute("allTrucks", truckService.getAllTrucksSummary());
        return "trucks";
    }


}
