package org.softuni.Rent_Vehicle_Company.web.controller;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.softuni.Rent_Vehicle_Company.model.entity.Role;
import org.softuni.Rent_Vehicle_Company.model.entity.User;
import org.softuni.Rent_Vehicle_Company.model.enums.UserRoleEnum;
import org.softuni.Rent_Vehicle_Company.repository.RoleRepository;
import org.softuni.Rent_Vehicle_Company.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class RegisterControllerIT {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepository roleRepository;


    @Autowired
    private MockMvc mockMvc;



    @Test
    void testRegistration() throws Exception {

        mockMvc.perform(post("/register")
                        .param("username", "yoan")
                        .param("email", "yoan@example.com")
                        .param("gender", "male")
                        .param("password", "1234")
                        .param("roles", "ADMIN")
                                .with(csrf())
                ).andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/login"));

        Optional<User> user = userRepository.findByUsername("yoan");

        Assertions.assertTrue(user.isPresent());

            User userEntity = user.get();


        Assertions.assertEquals("yoan@example.com", userEntity.getEmail());
        Assertions.assertEquals("male", userEntity.getGender());
        Assertions.assertTrue(passwordEncoder.matches("1234", userEntity.getPassword()));


    }
}
