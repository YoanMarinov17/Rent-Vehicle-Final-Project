package org.softuni.Rent_Vehicle_Company.controller;


import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.softuni.Rent_Vehicle_Company.model.dto.VehicleDto;
import org.softuni.Rent_Vehicle_Company.model.entity.Car;
import org.softuni.Rent_Vehicle_Company.model.entity.Truck;
import org.softuni.Rent_Vehicle_Company.model.entity.Van;
import org.softuni.Rent_Vehicle_Company.model.entity.Vehicle;
import org.softuni.Rent_Vehicle_Company.model.enums.EngineEnum;
import org.softuni.Rent_Vehicle_Company.model.enums.TypeCar;
import org.softuni.Rent_Vehicle_Company.model.enums.TypeEnum;
import org.softuni.Rent_Vehicle_Company.service.VehicleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


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
    private final ModelMapper modelMapper;

    public VehicleController(VehicleService vehicleService, ModelMapper modelMapper) {
        this.vehicleService = vehicleService;
        this.modelMapper = modelMapper;
    }


    @GetMapping("/add-vehicle")
    public String getVehicle(Model model) {


        model.addAttribute("vehicleDto", new VehicleDto());

        return "add-vehicle";
    }

    @PostMapping("/add-vehicle")
    public String createVehicle(Model model, @Valid  @ModelAttribute VehicleDto vehicleDto,
                                @RequestParam TypeEnum vehicleType, @RequestParam int horsePower,
                                @RequestParam TypeCar carType, @RequestParam int numberDoors,
                                @RequestParam int numberSeats, @RequestParam int numberOfVanSeats,
                                @RequestParam int upTones) {



        switch (vehicleType){
            case CAR -> vehicleService.createCar(vehicleDto,carType, horsePower, numberDoors, numberSeats);
            case TRUCK -> vehicleService.createTruck(vehicleDto,upTones);
            case VAN -> vehicleService.createVan(vehicleDto,numberOfVanSeats);
        }


        return "redirect:/index";
    }


}
