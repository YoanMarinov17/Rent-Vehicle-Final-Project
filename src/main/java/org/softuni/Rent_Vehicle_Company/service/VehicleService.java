package org.softuni.Rent_Vehicle_Company.service;

import org.softuni.Rent_Vehicle_Company.model.dto.CarDto;
import org.softuni.Rent_Vehicle_Company.model.dto.TruckDto;
import org.softuni.Rent_Vehicle_Company.model.dto.VanDto;
import org.softuni.Rent_Vehicle_Company.model.entity.Reservation;
import org.softuni.Rent_Vehicle_Company.model.entity.User;
import org.softuni.Rent_Vehicle_Company.model.entity.Vehicle;
import org.springframework.data.crossstore.ChangeSetPersister;

import java.security.Principal;
import java.util.List;
import java.util.Map;

public interface VehicleService {

    void createCar(CarDto carDto, Principal principal);
    void createVan(VanDto vanDto, Principal principal);
    void createTruck(TruckDto truckDto, Principal principal);


    List<Vehicle> getAllVehiclesByUser(Principal principal);

    Vehicle getVehicleDetails(Long id) throws ChangeSetPersister.NotFoundException;

    void deleteOffer(Long id);


    Map<Vehicle, Reservation> findAllByUserId(long id);
}

