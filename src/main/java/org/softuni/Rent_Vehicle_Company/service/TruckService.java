package org.softuni.Rent_Vehicle_Company.service;

import org.softuni.Rent_Vehicle_Company.model.entity.Truck;
import org.springframework.data.crossstore.ChangeSetPersister;

import java.util.List;
import java.util.Optional;

public interface TruckService {
    List<Truck> getAllTrucksSummary();

    Truck getTruckDetails(Long id) throws ChangeSetPersister.NotFoundException;
}
