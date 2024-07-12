package org.softuni.Rent_Vehicle_Company.service.impl;


import org.modelmapper.ModelMapper;
import org.softuni.Rent_Vehicle_Company.model.dto.CarDto;
import org.softuni.Rent_Vehicle_Company.model.dto.TruckDto;
import org.softuni.Rent_Vehicle_Company.model.dto.VanDto;
import org.softuni.Rent_Vehicle_Company.model.entity.*;
import org.softuni.Rent_Vehicle_Company.model.enums.TypeEnum;
import org.softuni.Rent_Vehicle_Company.repository.VehicleRepository;
import org.softuni.Rent_Vehicle_Company.service.UserService;
import org.softuni.Rent_Vehicle_Company.service.VehicleService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class VehicleServiceImpl implements VehicleService {

    private final VehicleRepository vehicleRepository;
    private final ModelMapper modelMapper;

    private final UserService userService;


    public VehicleServiceImpl(VehicleRepository vehicleRepository, ModelMapper modelmapper, UserService userService) {
        this.vehicleRepository = vehicleRepository;
        this.modelMapper = modelmapper;

        this.userService = userService;
    }



    @Override
    public void createCar(CarDto carDto) {

        Car car = modelMapper.map(carDto, Car.class);
        car.setType(TypeEnum.CAR);

        vehicleRepository.save(car);
    }

    @Override
    public void createVan(VanDto vanDto) {

        Van van = modelMapper.map(vanDto, Van.class);
        van.setType(TypeEnum.VAN);



        vehicleRepository.save(van);

    }

    @Override
    public void createTruck(TruckDto truckDto) {

        Truck truck = modelMapper.map(truckDto, Truck.class);

        truck.setType(TypeEnum.TRUCK);



        vehicleRepository.save(truck);
    }

}
