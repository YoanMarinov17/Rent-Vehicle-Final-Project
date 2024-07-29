package org.softuni.Rent_Vehicle_Company.controller;


import org.softuni.Rent_Vehicle_Company.model.dto.CarDto;
import org.softuni.Rent_Vehicle_Company.model.enums.EngineEnum;
import org.softuni.Rent_Vehicle_Company.model.enums.TypeCar;
import org.softuni.Rent_Vehicle_Company.service.CarService;
import org.softuni.Rent_Vehicle_Company.service.VehicleService;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequestMapping("/cars")
public class CarController {

    @ModelAttribute("allCarsType")
    public TypeCar[] allCarsTypes() {
        return TypeCar.values();
    }
    @ModelAttribute("allEngineTypes")
    public EngineEnum[] allEngineTypes() {
        return EngineEnum.values();
    }


    private final VehicleService vehicleService;

    private final CarService carService;

    public CarController(VehicleService vehicleService, CarService carService) {
        this.vehicleService = vehicleService;
        this.carService = carService;
    }

    //CREATE CAR
    @GetMapping("/add-car")
    public String getCar(Model model) {

        model.addAttribute("carDto", new CarDto());
        return "add-car";
    }

    //ADD CAR TO DB
    @PostMapping("/add-car")
    public String createCar(CarDto carDto, Principal principal) {

        vehicleService.createCar(carDto, principal);


        return "redirect:/cars";
    }


    //GETTING ALL CARS FROM DB - LIST
    @GetMapping("")
    public String getAllCars(Model model) {

        model.addAttribute("allCars", carService.getAllCarSummary());
        return "cars";
    }






    @GetMapping("/car-details/{id}")
    public String carDetails(@PathVariable("id") Long id,
                                 Model model) throws ChangeSetPersister.NotFoundException {

        model.addAttribute("carDetails", carService.getCarDetails(id));

        return "car-details";
    }


    @DeleteMapping("/car-details/{id}")
    public String deleteCar(@PathVariable("id") Long id) {

        carService.deleteOffer(id);

        return "redirect:/cars";
    }




}
