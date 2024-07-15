package org.softuni.Rent_Vehicle_Company.service;

import org.softuni.Rent_Vehicle_Company.model.dto.UserRegisterDto;
import org.softuni.Rent_Vehicle_Company.model.entity.User;

import java.util.Optional;

public  interface UserService {


     void register(UserRegisterDto data);

     public boolean userExist(String username);


     public boolean emailExist(String email);


    Optional<User> findById(Long userId);

    Optional<User> findByUsername(String username);
}
