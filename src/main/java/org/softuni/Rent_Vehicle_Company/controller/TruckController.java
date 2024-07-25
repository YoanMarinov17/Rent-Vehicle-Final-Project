package org.softuni.Rent_Vehicle_Company.controller;


import org.softuni.Rent_Vehicle_Company.model.dto.TruckDto;
import org.softuni.Rent_Vehicle_Company.model.enums.EngineEnum;
import org.softuni.Rent_Vehicle_Company.model.enums.TypeEnum;
import org.softuni.Rent_Vehicle_Company.service.TruckService;
import org.softuni.Rent_Vehicle_Company.service.VehicleService;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
public class TruckController {

    private final VehicleService vehicleService;
    private final TruckService truckService;

    public TruckController(VehicleService vehicleService, TruckService truckService) {
        this.vehicleService = vehicleService;
        this.truckService = truckService;
    }

    @ModelAttribute("allVehicleTypes")
    public TypeEnum[] vehicleTypes() {
        return TypeEnum.values();
    }
    @ModelAttribute("allEngineTypes")
    public EngineEnum[] allEngineTypes() {
        return EngineEnum.values();
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


    @GetMapping("/trucks")
    public String getAllTrucks(Model model) {

        model.addAttribute("allTrucks", truckService.getAllTrucksSummary());
        return "trucks";
    }



//
    @GetMapping("/truck-details/{id}")
    public String truckDetails(@PathVariable("id") Long id,
                                 Model model) throws ChangeSetPersister.NotFoundException {

        model.addAttribute("truckDetails", truckService.getTruckDetails(id));

        return "truck-details";
    }


    @DeleteMapping("/truck-details/{id}")
    public String deleteTruck(@PathVariable("id") Long id) {

        truckService.deleteOffer(id);

        return "redirect:/trucks";
    }
}
