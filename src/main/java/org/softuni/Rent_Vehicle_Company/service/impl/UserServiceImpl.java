package org.softuni.Rent_Vehicle_Company.service.impl;


import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.softuni.Rent_Vehicle_Company.model.entity.Role;
import org.softuni.Rent_Vehicle_Company.model.entity.User;
import org.softuni.Rent_Vehicle_Company.model.dto.UserRegisterDto;
import org.softuni.Rent_Vehicle_Company.model.enums.UserRoleEnum;
import org.softuni.Rent_Vehicle_Company.repository.RoleRepository;
import org.softuni.Rent_Vehicle_Company.repository.UserRepository;
import org.softuni.Rent_Vehicle_Company.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserServiceImpl implements UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;


    private final RoleRepository roleRepository;

    public UserServiceImpl(PasswordEncoder passwordEncoder, UserRepository userRepository, ModelMapper modelMapper, RoleRepository roleRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;

        this.roleRepository = roleRepository;
    }
@Transactional
    public User register(UserRegisterDto data) {

        User user = modelMapper.map(data, User.class);
        user.setPassword(passwordEncoder.encode(data.getPassword()));

        boolean isFirstUser = userRepository.count() == 0 ;
        boolean isSecondUser = userRepository.count() == 1;

        if (isFirstUser) {
            // Assign ADMIN role
            Role admin = roleRepository.findByRole(UserRoleEnum.ADMIN);
            Role moderator = roleRepository.findByRole(UserRoleEnum.MODERATOR);
            Role userRole = roleRepository.findByRole(UserRoleEnum.USER);

            user.setRoles(List.of(admin, moderator, userRole));



        } else if (isSecondUser) {

            Role moderator = roleRepository.findByRole(UserRoleEnum.MODERATOR);
            Role userRole = roleRepository.findByRole(UserRoleEnum.USER);
            user.setRoles(List.of(moderator, userRole));
        } else {

            Role userRole = roleRepository.findByRole(UserRoleEnum.USER);
            user.setRoles(List.of(userRole));
        }


        userRepository.save(user);


        return user;

    }


    @Override
    //Ako user-a съществува ще върне true / ако не съществува - false
    public boolean userExist(String username) {
        return this.userRepository.findByUsername(username).isPresent();
    }

    @Override
    //Ako email-a съществува ще върне true / ако не съществува - false
    public boolean emailExist(String email) {
        return this.userRepository.findByEmail(email).isPresent();
    }
}

