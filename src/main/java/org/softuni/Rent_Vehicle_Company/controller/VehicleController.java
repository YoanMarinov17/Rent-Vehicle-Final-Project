package org.softuni.Rent_Vehicle_Company.controller;


import jakarta.validation.Valid;
import org.softuni.Rent_Vehicle_Company.model.dto.VehicleDto;
import org.softuni.Rent_Vehicle_Company.model.enums.EngineEnum;
import org.softuni.Rent_Vehicle_Company.model.enums.TypeCar;
import org.softuni.Rent_Vehicle_Company.model.enums.TypeEnum;
import org.softuni.Rent_Vehicle_Company.service.VehicleService;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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


    @GetMapping("/add-vehicle")
    public String getVehicle(Model model) {

        model.addAttribute("vehicleDto", new VehicleDto());

        return  "add-vehicle";
    }


    @PostMapping("/add-vehicle")
    @ResponseBody
    public String createVehicle(
             @Valid @ModelAttribute VehicleDto vehicleDto, Model model) {

        model.addAttribute("vehicleDto", vehicleDto);
        vehicleService.createVehicle(vehicleDto);

        return "redirect:/index";
    }


}
