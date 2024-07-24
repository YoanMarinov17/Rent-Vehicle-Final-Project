package org.softuni.Rent_Vehicle_Company.controller;


import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(){

        return "index";
    }





    @GetMapping("/services")
    public ModelAndView service() {
        return new ModelAndView("services");
    }


    @GetMapping("/pricing")
    public ModelAndView price() {
        return new ModelAndView("pricing");
    }

    @GetMapping("/about")
    public ModelAndView about() {
        return new ModelAndView("about");
    }

    @GetMapping("/contact")
    public ModelAndView contact() {
        return new ModelAndView("contact");
    }

    @GetMapping("/car")
    public ModelAndView car() {
        return new ModelAndView("cars");
    }



    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String admin(){

        return "admin";
    }





}
