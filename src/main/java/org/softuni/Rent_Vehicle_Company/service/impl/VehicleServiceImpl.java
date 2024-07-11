package org.softuni.Rent_Vehicle_Company.service.impl;


import org.modelmapper.ModelMapper;
import org.softuni.Rent_Vehicle_Company.model.dto.VehicleDto;
import org.softuni.Rent_Vehicle_Company.model.entity.Car;
import org.softuni.Rent_Vehicle_Company.model.entity.Truck;
import org.softuni.Rent_Vehicle_Company.model.entity.Van;

import org.softuni.Rent_Vehicle_Company.model.enums.TypeCar;
import org.softuni.Rent_Vehicle_Company.repository.TruckRepository;
import org.softuni.Rent_Vehicle_Company.repository.VehicleRepository;
import org.softuni.Rent_Vehicle_Company.service.VehicleService;
import org.springframework.stereotype.Service;

@Service
public class VehicleServiceImpl implements VehicleService {

    private final VehicleRepository vehicleRepository;
    private final ModelMapper modelMapper;


    public VehicleServiceImpl(VehicleRepository vehicleRepository, ModelMapper modelmapper) {
        this.vehicleRepository = vehicleRepository;
        this.modelMapper = modelmapper;

    }

    @Override
    public void createTruck(VehicleDto vehicleDto, int upTones) {
        Truck truck = new Truck();
        //Data from DTO
        truck.setType(vehicleDto.getType());
        truck.setEngine(vehicleDto.getEngine());
        truck.setModel(vehicleDto.getModel());
        truck.setYear(vehicleDto.getYear());

        //Data from @RequestParam
        modelMapper.map(vehicleDto, Truck.class);

//        truck.setUpTones(upTones);
        vehicleRepository.save(truck);
    }



    @Override
    public void createCar(VehicleDto vehicleDto, TypeCar type, int horsePower, int numberSeats, int numberDoors) {
        Car car = new Car();

//        // Data from DTO
        car.setType(vehicleDto.getType());
        car.setEngine(vehicleDto.getEngine());
        car.setModel(vehicleDto.getModel());
        car.setYear(vehicleDto.getYear());

        //Data from @RequestParam
        modelMapper.map(vehicleDto, Car.class);
//        car.setCarType(type);
//        car.setHorsePower(horsePower);
//        car.setNumberOfDoors(numberDoors);
//        car.setNumberOfSeats(numberSeats);

        vehicleRepository.save(car);
    }



    @Override
    public void createVan(VehicleDto vehicleDto, int numberOfVanSeats) {

        Van van = new Van();
        //Data from DTO
        van.setType(vehicleDto.getType());
        van.setEngine(vehicleDto.getEngine());
        van.setModel(vehicleDto.getModel());
        van.setYear(vehicleDto.getYear());


        //Data from @RequestParam
        modelMapper.map(vehicleDto, Van.class);
//        van.setNumberOfVanSeats(numberOfVanSeats);

        vehicleRepository.save(van);
    }


















//        switch (vehicleDto.getType()) {
//            case CAR:
//                Vehicle car = new Car();
//                car.setType(vehicleDto.getType());
//                car.setYear(vehicleDto.getYear());
//                car.setModel(vehicleDto.getModel());
//                car.setEngine(vehicleDto.getEngine());
//
//                vehicleRepository.save(car);
//                break;
//
//            case VAN:
//                Vehicle van = new Van();
//                van.setType(vehicleDto.getType());
//                van.setYear(vehicleDto.getYear());
//                van.setModel(vehicleDto.getModel());
//                van.setEngine(vehicleDto.getEngine());
//
//                vehicleRepository.save(van);
//                break;
////
//            case TRUCK:

    //        truck.setType(vehicleDto.getType());
    //        truck.setModel(vehicleDto.getModel());
    //        truck.setYear(vehicleDto.getYear());
    //        truck.setEngine(vehicleDto.getEngine());

//
//                break;
//        }


}
