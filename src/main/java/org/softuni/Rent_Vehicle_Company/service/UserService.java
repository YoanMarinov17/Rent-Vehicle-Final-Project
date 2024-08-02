package org.softuni.Rent_Vehicle_Company.service;

import org.softuni.Rent_Vehicle_Company.model.dto.UserRegisterDto;
import org.softuni.Rent_Vehicle_Company.model.entity.Role;
import org.softuni.Rent_Vehicle_Company.model.entity.User;
import org.softuni.Rent_Vehicle_Company.model.entity.Vehicle;

import java.util.List;
import java.util.Map;

public  interface UserService {


     User register(UserRegisterDto data);

     public boolean userExist(String username);


     public boolean emailExist(String email);


    Map<User, Map<List<Vehicle>, List<Role>>>  findAllRolesByUser();



    User getCurrentUser(Long id);

    void deleteCurrentUser(Long id);
}
