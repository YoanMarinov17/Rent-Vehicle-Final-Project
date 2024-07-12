package org.softuni.Rent_Vehicle_Company.controller;


import jakarta.validation.Valid;
import org.softuni.Rent_Vehicle_Company.model.dto.UserLoginDto;
import org.softuni.Rent_Vehicle_Company.repository.UserRepository;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@Controller
public class LoginController {

    private final UserRepository userRepository;

    public LoginController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @ModelAttribute(name = "userLoginDto")
    public UserLoginDto userLoginForm() {
        return new UserLoginDto();
    }

    @GetMapping("/login")
    public ModelAndView viewLogin() {
        ModelAndView mv = new ModelAndView("login");
        mv.addObject("userLoginDto", new UserLoginDto());

        return mv;
    }

    @GetMapping("/login-error")
    public ModelAndView viewLoginError(@Valid UserLoginDto data, BindingResult bindingResult) {
        ModelAndView mv = new ModelAndView("login");


        if (bindingResult.hasErrors()){
            mv.addObject("errorMessage", true);
            mv.addObject("userLoginDto", new UserLoginDto());
        }


        return mv;
    }



}
