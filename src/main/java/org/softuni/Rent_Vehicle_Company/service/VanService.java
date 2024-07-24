package org.softuni.Rent_Vehicle_Company.service;

import org.softuni.Rent_Vehicle_Company.model.entity.Van;
import org.springframework.data.crossstore.ChangeSetPersister;

import java.util.List;
import java.util.Optional;

public interface VanService {
    List<Van> getAllVansSummary();

    Van getVanDetails(Long id) throws ChangeSetPersister.NotFoundException;

    void deleteOffer(Long id);

}
