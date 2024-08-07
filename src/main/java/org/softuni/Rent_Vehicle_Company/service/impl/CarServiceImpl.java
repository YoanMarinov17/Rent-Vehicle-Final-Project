package org.softuni.Rent_Vehicle_Company.service.impl;

import org.softuni.Rent_Vehicle_Company.model.entity.Car;
import org.softuni.Rent_Vehicle_Company.repository.CarRepository;
import org.softuni.Rent_Vehicle_Company.repository.VehicleRepository;
import org.softuni.Rent_Vehicle_Company.service.CarService;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class CarServiceImpl implements CarService {


    private final CarRepository carRepository;

    private final VehicleRepository vehicleRepository;

    public CarServiceImpl(CarRepository carRepository, VehicleRepository vehicleRepository) {
        this.carRepository = carRepository;
        this.vehicleRepository = vehicleRepository;
    }

    @Override
    public List<Car> getAllCarSummary() {
        List<Car> all = this.carRepository.findAll();
        return all;
    }

    @Override
    public Car getCarDetails(Long id) throws ChangeSetPersister.NotFoundException {
        Optional<Car> optionalCar = carRepository.findById(id);


        return optionalCar.orElseThrow(ChangeSetPersister.NotFoundException::new);


    }

    @Override
    public void deleteOffer(Long id) {

        carRepository.deleteById(id);

    }
}
