package org.softuni.Rent_Vehicle_Company.controller;


import org.softuni.Rent_Vehicle_Company.model.dto.VanDto;
import org.softuni.Rent_Vehicle_Company.model.enums.EngineEnum;
import org.softuni.Rent_Vehicle_Company.model.enums.TypeEnum;
import org.softuni.Rent_Vehicle_Company.service.VanService;
import org.softuni.Rent_Vehicle_Company.service.VehicleService;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequestMapping("/vans")
public class VanController {
    private final VehicleService vehicleService;
    private final VanService vanService;

    public VanController(VehicleService vehicleService, VanService vanService) {
        this.vehicleService = vehicleService;
        this.vanService = vanService;
    }

    @ModelAttribute("allVehicleTypes")
    public TypeEnum[] vehicleTypes() {
        return TypeEnum.values();
    }
    @ModelAttribute("allEngineTypes")
    public EngineEnum[] allEngineTypes() {
        return EngineEnum.values();
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


    @GetMapping("")
    public String getAllVans(Model model) {

        model.addAttribute("allVans", vanService.getAllVansSummary());
        return "vans";
    }



    @GetMapping("/van-details/{id}")
    public String vanDetails(@PathVariable("id") Long id,
                                 Model model) throws ChangeSetPersister.NotFoundException {

        model.addAttribute("vanDetails", vanService.getVanDetails(id));

        return "van-details";
    }


    @DeleteMapping("/van-details/{id}")
    public String deleteVan(@PathVariable("id") Long id) {

        vanService.deleteOffer(id);

        return "redirect:/vans";
    }
}
