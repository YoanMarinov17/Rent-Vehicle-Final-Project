package org.softuni.Rent_Vehicle_Company.service.impl;


import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.softuni.Rent_Vehicle_Company.model.entity.Role;
import org.softuni.Rent_Vehicle_Company.model.entity.User;
import org.softuni.Rent_Vehicle_Company.model.dto.UserRegisterDto;
import org.softuni.Rent_Vehicle_Company.model.entity.Vehicle;
import org.softuni.Rent_Vehicle_Company.model.enums.UserRoleEnum;
import org.softuni.Rent_Vehicle_Company.repository.RoleRepository;
import org.softuni.Rent_Vehicle_Company.repository.UserRepository;
import org.softuni.Rent_Vehicle_Company.service.UserService;
import org.softuni.Rent_Vehicle_Company.service.VehicleService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;


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
    public void register(UserRegisterDto data) {

        User user = modelMapper.map(data, User.class);
        user.setPassword(passwordEncoder.encode(data.getPassword()));

        boolean isFirstUser = userRepository.count() == 0;
        boolean isSecondUser = userRepository.count() == 1 || userRepository.count() == 2;

        if (isFirstUser) {
            // Assign ADMIN role
            Role admin = roleRepository.findByRole(UserRoleEnum.ADMIN);
            user.setRoles(List.of(admin));
        } else if (isSecondUser) {
            Role moderator = roleRepository.findByRole(UserRoleEnum.MODERATOR);
            user.setRoles(List.of(moderator));
        } else {
            Role userRole = roleRepository.findByRole(UserRoleEnum.USER);
            user.setRoles(List.of(userRole));
        }
        userRepository.save(user);
    }

    @Override
    public boolean userExist(String username) {
        return this.userRepository.findByUsername(username).isPresent();
    }

    @Override
    public boolean emailExist(String email) {
        return this.userRepository.findByEmail(email).isPresent();
    }

    @Override
    public Map<User, Map<List<Vehicle>, List<Role>>> findAllRolesByUser() {
        List<User> all = userRepository.findAll();
        Map<User, Map<List<Vehicle>, List<Role>>> userVehiclesRolesMap = new LinkedHashMap<>();

        for (User user : all) {
            List<Vehicle> userVehicles = new ArrayList<>(user.getVehicles());
            List<Role> userRoles = new ArrayList<>(user.getRoles());
            Map<List<Vehicle>, List<Role>> vehiclesRolesMap = new LinkedHashMap<>();
            vehiclesRolesMap.put(userVehicles, userRoles);
            userVehiclesRolesMap.put(user, vehiclesRolesMap);
        }
        return userVehiclesRolesMap;
    }

    @Override
    public User getCurrentUser(Long id) {
        Optional<User> byId = userRepository.findById(id);
        return byId.get();
    }

    @Override
    public void deleteCurrentUser(Long id) {
        userRepository.deleteById(id);
    }
}