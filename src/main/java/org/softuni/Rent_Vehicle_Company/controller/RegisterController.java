package org.softuni.Rent_Vehicle_Company.controller;

import jakarta.validation.Valid;
import org.softuni.Rent_Vehicle_Company.entity.User;
import org.softuni.Rent_Vehicle_Company.entity.dto.UserRegisterDto;
import org.softuni.Rent_Vehicle_Company.repository.UserRepository;
import org.softuni.Rent_Vehicle_Company.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
public class RegisterController {

    private final UserServiceImpl userService;
    private final UserRepository userRepository;

    @Autowired
    public RegisterController(UserServiceImpl userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @ModelAttribute(name = "userRegisterDto")
    public UserRegisterDto userRegistrationForm() {
        return new UserRegisterDto();
    }


    @GetMapping("/register")
    public String viewRegister(Model model) {
        model.addAttribute("registerData", new UserRegisterDto());
        return "registration";
    }


    // Правим Пост заявка за да извлечем данните от ДТО и да ги добавим към базата
    @PostMapping("/register")
    public ModelAndView doRegister(@Valid UserRegisterDto data, BindingResult bindingResult, ModelAndView model, RedirectAttributes ra) {


//        Check if username already exist in the DB.
        if (userService.userExist(data.getUsername())) {
            bindingResult.addError(new FieldError("userRegisterDto",
                    "username",
                    "- Username already exists !"));
        }


//Check if email already exist in the DB.
        if (userService.emailExist(data.getEmail())) {
            bindingResult.addError(new FieldError("userRegisterDto",
                    "email",
                    "- Email already exists !"));
        }

        //Check if password matches the confirmPassword
        if (data.getPassword() != null && data.getConfirmPassword() != null){
            if (!data.getPassword().equals(data.getConfirmPassword())){
                    bindingResult.addError(new FieldError("userRegisterDto", "confirmPassword", "- Passwords must match !"));
            }
        }



        if (bindingResult.hasErrors()) {
            model.setViewName("registration");
            return model;
        }



        //Show successful message for registration
        ra.addFlashAttribute("message", "Congratulations ! Your registration is ready.");

// Register user in the DB.
        userService.register(data);

        model.setViewName("redirect:/login");
        return model;

    }

}
