package org.softuni.Rent_Vehicle_Company.service;

import org.softuni.Rent_Vehicle_Company.model.dto.VehicleDto;
import org.softuni.Rent_Vehicle_Company.model.entity.Car;
import org.softuni.Rent_Vehicle_Company.model.entity.Truck;
import org.softuni.Rent_Vehicle_Company.model.entity.Van;
import org.softuni.Rent_Vehicle_Company.model.entity.Vehicle;
import org.softuni.Rent_Vehicle_Company.model.enums.TypeCar;

public interface VehicleService {
    void createTruck(VehicleDto addOfferDTO, int upTones);
    void createCar(VehicleDto addOfferDTO, TypeCar type, int horsePower, int numberSeats, int numberDoors);
    void createVan(VehicleDto addOfferDTO, int numberSeats);
}
