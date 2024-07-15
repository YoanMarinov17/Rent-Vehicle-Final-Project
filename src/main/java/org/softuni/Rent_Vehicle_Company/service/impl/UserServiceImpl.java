package org.softuni.Rent_Vehicle_Company.service.impl;


import org.modelmapper.ModelMapper;
import org.softuni.Rent_Vehicle_Company.model.entity.User;
import org.softuni.Rent_Vehicle_Company.model.dto.UserRegisterDto;
import org.softuni.Rent_Vehicle_Company.repository.RoleRepository;
import org.softuni.Rent_Vehicle_Company.repository.UserRepository;
import org.softuni.Rent_Vehicle_Company.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;


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

    public void register(UserRegisterDto data) {

        User user = modelMapper.map(data, User.class);
        user.setPassword(passwordEncoder.encode(data.getPassword()));

        userRepository.save(user);
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




    @Override
    public Optional<User> findById(Long userId) {
        return userRepository.findById(userId);
    }



    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }



}
