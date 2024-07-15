package org.softuni.Rent_Vehicle_Company.service.impl;


import org.modelmapper.ModelMapper;
import org.softuni.Rent_Vehicle_Company.model.dto.CarDto;
import org.softuni.Rent_Vehicle_Company.model.dto.TruckDto;
import org.softuni.Rent_Vehicle_Company.model.dto.VanDto;
import org.softuni.Rent_Vehicle_Company.model.entity.*;
import org.softuni.Rent_Vehicle_Company.model.enums.TypeEnum;
import org.softuni.Rent_Vehicle_Company.repository.UserRepository;
import org.softuni.Rent_Vehicle_Company.repository.VehicleRepository;
import org.softuni.Rent_Vehicle_Company.service.UserService;
import org.softuni.Rent_Vehicle_Company.service.VehicleService;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Optional;

@Service
public class VehicleServiceImpl implements VehicleService {

    private final VehicleRepository vehicleRepository;
    private final ModelMapper modelMapper;

    private final UserService userService;

    private final UserRepository userRepository;


    public VehicleServiceImpl(VehicleRepository vehicleRepository, ModelMapper modelMapper, UserService userService, UserRepository userRepository) {
        this.vehicleRepository = vehicleRepository;
        this.modelMapper = modelMapper;
        this.userService = userService;
        this.userRepository = userRepository;

    }


    @Override
    public void createCar(CarDto carDto, Principal principal) {

        String name = principal.getName();

        Optional<User> optionalUser = userRepository.findByUsername(name);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            Car car = modelMapper.map(carDto, Car.class);
            car.setType(TypeEnum.CAR);
            car.setUser(user);
            vehicleRepository.save(car);
        }
    }

    @Override
    public void createVan(VanDto vanDto, Principal principal) {

        String name = principal.getName();

        Optional<User> optionalUser = userRepository.findByUsername(name);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            Van van = modelMapper.map(vanDto, Van.class);
            van.setType(TypeEnum.VAN);
            van.setUser(user);
            vehicleRepository.save(van);

        }
    }

    @Override
    public void createTruck(TruckDto truckDto, Principal principal) {

        String name = principal.getName();

        Optional<User> optionalUser = userRepository.findByUsername(name);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            Truck truck = modelMapper.map(truckDto, Truck.class);
            truck.setType(TypeEnum.TRUCK);
            truck.setUser(user);
            vehicleRepository.save(truck);

        }

    }


}
