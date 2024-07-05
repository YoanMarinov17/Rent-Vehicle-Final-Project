package org.softuni.Rent_Vehicle_Company.service;

import org.softuni.Rent_Vehicle_Company.entity.User;
import org.softuni.Rent_Vehicle_Company.entity.dto.UserRegisterDto;
import org.springframework.ui.Model;

import java.util.Optional;

public  interface UserService {

     void register(UserRegisterDto data);

     public boolean userExist(String username);


     public boolean emailExist(String email);


}
