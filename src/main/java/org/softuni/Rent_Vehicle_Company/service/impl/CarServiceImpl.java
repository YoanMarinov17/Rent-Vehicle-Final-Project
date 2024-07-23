package org.softuni.Rent_Vehicle_Company.service.impl;

import jakarta.persistence.Cache;
import org.softuni.Rent_Vehicle_Company.model.entity.Car;
import org.softuni.Rent_Vehicle_Company.repository.CarRepository;
import org.softuni.Rent_Vehicle_Company.service.CarService;
import org.springframework.stereotype.Service;

import java.util.List;



@Service
public class CarServiceImpl implements CarService {



    private final CarRepository carRepository;

    public CarServiceImpl(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Override
    public List<Car> getAllCarSummary() {
        List<Car> all = this.carRepository.findAll();
        return all;
    }
}
