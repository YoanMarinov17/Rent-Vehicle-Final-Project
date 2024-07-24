package org.softuni.Rent_Vehicle_Company.service;

import org.softuni.Rent_Vehicle_Company.model.dto.CarDto;
import org.softuni.Rent_Vehicle_Company.model.entity.Car;
import org.softuni.Rent_Vehicle_Company.model.entity.Vehicle;
import org.springframework.data.crossstore.ChangeSetPersister;

import java.util.List;
import java.util.Optional;

public interface CarService {

    List<Car> getAllCarSummary();

    Car getCarDetails(Long id) throws ChangeSetPersister.NotFoundException;

    void deleteOffer(Long id);
}
