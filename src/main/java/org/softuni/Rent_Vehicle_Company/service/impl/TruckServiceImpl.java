package org.softuni.Rent_Vehicle_Company.service.impl;

import org.softuni.Rent_Vehicle_Company.model.entity.Truck;
import org.softuni.Rent_Vehicle_Company.repository.TruckRepository;
import org.softuni.Rent_Vehicle_Company.service.TruckService;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class TruckServiceImpl implements TruckService {

    private final TruckRepository truckRepository;

    public TruckServiceImpl(TruckRepository truckRepository) {
        this.truckRepository = truckRepository;
    }

    @Override
    public List<Truck> getAllTrucksSummary() {
        List<Truck> all = this.truckRepository.findAll();
        return all;
    }
}
