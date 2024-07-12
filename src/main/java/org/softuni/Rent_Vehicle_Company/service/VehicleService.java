package org.softuni.Rent_Vehicle_Company.service;

import org.softuni.Rent_Vehicle_Company.model.dto.CarDto;
import org.softuni.Rent_Vehicle_Company.model.dto.TruckDto;
import org.softuni.Rent_Vehicle_Company.model.dto.VanDto;
import org.softuni.Rent_Vehicle_Company.model.dto.VehicleDto;
import org.softuni.Rent_Vehicle_Company.model.enums.TypeCar;

public interface VehicleService {

    void createCar(CarDto carDto);
    void createVan(VanDto vanDto);
    void createTruck(TruckDto truckDto);
}
