package org.softuni.Rent_Vehicle_Company.service;

import org.softuni.Rent_Vehicle_Company.model.dto.UserRegisterDto;
import org.softuni.Rent_Vehicle_Company.model.entity.Role;
import org.softuni.Rent_Vehicle_Company.model.entity.User;

import java.util.List;
import java.util.Optional;

public  interface UserService {


     User register(UserRegisterDto data);

     public boolean userExist(String username);


     public boolean emailExist(String email);




}
