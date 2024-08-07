package org.softuni.Rent_Vehicle_Company.service.impl;


import org.modelmapper.ModelMapper;
import org.softuni.Rent_Vehicle_Company.model.dto.CarDto;
import org.softuni.Rent_Vehicle_Company.model.dto.TruckDto;
import org.softuni.Rent_Vehicle_Company.model.dto.VanDto;

import org.softuni.Rent_Vehicle_Company.model.entity.*;

import org.softuni.Rent_Vehicle_Company.model.enums.TypeEnum;

import org.softuni.Rent_Vehicle_Company.repository.UserRepository;
import org.softuni.Rent_Vehicle_Company.repository.VehicleRepository;

import org.softuni.Rent_Vehicle_Company.service.VehicleService;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.*;


@Service
public class VehicleServiceImpl implements VehicleService {

    private final VehicleRepository vehicleRepository;
    private final ModelMapper modelMapper;

    private final UserRepository userRepository;




    public VehicleServiceImpl(VehicleRepository vehicleRepository, ModelMapper modelMapper, UserRepository userRepository) {
        this.vehicleRepository = vehicleRepository;
        this.modelMapper = modelMapper;
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
            car.setIsReserved(false);
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
            van.setIsReserved(false);
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
            truck.setIsReserved(false);
            vehicleRepository.save(truck);

        }

    }

    @Override
    public List<Vehicle> getAllVehiclesByUser(Principal principal) {


        Optional<User> optionalUser = userRepository.findByUsername(principal.getName());
        List<Vehicle> allVehicles = new ArrayList<>();


        if (optionalUser.isPresent()) {
            long id = optionalUser.get().getId();
            allVehicles = vehicleRepository.findAll().stream().filter(vehicle -> vehicle.getUser().getId() == id).toList();

        }

        return allVehicles;
    }


    @Override
    public Vehicle getVehicleDetails(Long id) throws ChangeSetPersister.NotFoundException {
        Optional<Vehicle> optionalVehicle = vehicleRepository.findById(id);


        return optionalVehicle.orElseThrow(ChangeSetPersister.NotFoundException::new);
    }

    @Override
    public void deleteOffer(Long id) {

        vehicleRepository.deleteById(id);
    }




}
