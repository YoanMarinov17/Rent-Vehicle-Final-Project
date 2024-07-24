package org.softuni.Rent_Vehicle_Company.service.impl;

import org.softuni.Rent_Vehicle_Company.model.entity.Car;
import org.softuni.Rent_Vehicle_Company.model.entity.Truck;
import org.softuni.Rent_Vehicle_Company.model.entity.Van;
import org.softuni.Rent_Vehicle_Company.repository.TruckRepository;
import org.softuni.Rent_Vehicle_Company.service.TruckService;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


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

    @Override
    public Truck getTruckDetails(Long id) throws ChangeSetPersister.NotFoundException {
        Optional<Truck> optionalTruck = truckRepository.findById(id);


        return  optionalTruck.orElseThrow(ChangeSetPersister.NotFoundException::new);
    }



    @Override
    public void deleteOffer(Long id) {


        truckRepository.deleteById(id);

    }
}
