package org.softuni.Rent_Vehicle_Company.service.impl;

import org.softuni.Rent_Vehicle_Company.model.entity.Van;
import org.softuni.Rent_Vehicle_Company.repository.VanRepository;
import org.softuni.Rent_Vehicle_Company.service.VanService;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class VanServiceImpl implements VanService {

    private final VanRepository vanRepository;

    public VanServiceImpl(VanRepository vanRepository) {
        this.vanRepository = vanRepository;
    }

    @Override
    public List<Van> getAllVansSummary() {
        List<Van> all = vanRepository.findAll();
        return all;
    }
}
