package org.softuni.Rent_Vehicle_Company.service;

import org.softuni.Rent_Vehicle_Company.model.dto.CarDto;
import org.softuni.Rent_Vehicle_Company.model.dto.TruckDto;
import org.softuni.Rent_Vehicle_Company.model.dto.VanDto;
import org.softuni.Rent_Vehicle_Company.model.dto.VehicleDto;
import org.softuni.Rent_Vehicle_Company.model.enums.TypeCar;

import java.security.Principal;

public interface VehicleService {

    void createCar(CarDto carDto, Principal principal);
    void createVan(VanDto vanDto, Principal principal);
    void createTruck(TruckDto truckDto, Principal principal);
}
