package org.softuni.Rent_Vehicle_Company.service;

import org.softuni.Rent_Vehicle_Company.model.dto.UserRegisterDto;

public  interface UserService {

     void register(UserRegisterDto data);

     public boolean userExist(String username);


     public boolean emailExist(String email);


}
