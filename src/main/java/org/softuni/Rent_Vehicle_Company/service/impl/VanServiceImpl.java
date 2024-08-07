package org.softuni.Rent_Vehicle_Company.service.impl;

import org.softuni.Rent_Vehicle_Company.model.entity.Car;
import org.softuni.Rent_Vehicle_Company.model.entity.Van;
import org.softuni.Rent_Vehicle_Company.repository.VanRepository;
import org.softuni.Rent_Vehicle_Company.repository.VehicleRepository;
import org.softuni.Rent_Vehicle_Company.service.VanService;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class VanServiceImpl implements VanService {

    private final VanRepository vanRepository;
    private final VehicleRepository vehicleRepository;

    public VanServiceImpl(VanRepository vanRepository, VehicleRepository vehicleRepository) {
        this.vanRepository = vanRepository;
        this.vehicleRepository = vehicleRepository;
    }

    @Override
    public List<Van> getAllVansSummary() {
        List<Van> all = vanRepository.findAll();


        return all;
    }

    @Override
    public Van getVanDetails(Long id) throws ChangeSetPersister.NotFoundException {

        Optional<Van> optionalVan = vanRepository.findById(id);


        return optionalVan.orElseThrow(ChangeSetPersister.NotFoundException::new);
    }

    @Override
    public void deleteOffer(Long id) {

        vanRepository.deleteById(id);


    }
}
