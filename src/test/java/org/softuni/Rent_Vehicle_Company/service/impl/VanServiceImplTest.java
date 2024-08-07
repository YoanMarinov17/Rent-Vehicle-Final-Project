package org.softuni.Rent_Vehicle_Company.service.impl;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.softuni.Rent_Vehicle_Company.model.entity.Car;
import org.softuni.Rent_Vehicle_Company.model.entity.Van;
import org.softuni.Rent_Vehicle_Company.model.enums.EngineEnum;
import org.softuni.Rent_Vehicle_Company.model.enums.TypeEnum;
import org.softuni.Rent_Vehicle_Company.repository.VanRepository;
import org.softuni.Rent_Vehicle_Company.repository.VehicleRepository;
import org.springframework.data.crossstore.ChangeSetPersister;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class VanServiceImplTest {


    @InjectMocks
    private VanServiceImpl toTest;


    @Mock
    private VanRepository mockVanRepository;


    @Mock
    private VehicleRepository mockVehicleRepository;


    @BeforeEach
    void setUp() {
        toTest = new VanServiceImpl(mockVanRepository, mockVehicleRepository);
    }


    @Test
    void getAllVanSummaryTest() {

        Van van1 = new Van();
        Van van2 = new Van();
        Van van3 = new Van();
        List<Van> mockVans = Arrays.asList(van1, van2, van3);

        Mockito.when(mockVanRepository.findAll())
                .thenReturn(mockVans);


        List<Van> allCars = toTest.getAllVansSummary();


        assertNotNull(mockVans);
        assertEquals(3, mockVans.size());
    }

    @Test
    void getVanDetailsTest() throws ChangeSetPersister.NotFoundException {
        Van van = new Van();
        van.setId(1L);

        van.setType(TypeEnum.VAN);
        van.setYear(2020);
        van.setModel("X5");
        van.setBrand("BMW");
        van.setPricePerDay(500);
        van.setNumberOfVanSeats(5);
        van.setIsReserved(false);
        van.setEngine(EngineEnum.PETROL);
        van.setImageUrl("asd");

        Mockito.when(mockVanRepository.findById(1L)).thenReturn(Optional.of(van));

        Van result = toTest.getVanDetails(van.getId());

        assertEquals(1L, result.getId());
        assertEquals(TypeEnum.VAN, result.getType());
        assertEquals(2020, result.getYear());
        assertEquals("X5", result.getModel());
        assertEquals("BMW", result.getBrand());
        assertEquals(500, result.getPricePerDay());
        assertEquals(5, result.getNumberOfVanSeats());
        assertEquals(false, result.getIsReserved());
        assertEquals("asd", result.getImageUrl());
    }


    @Test
    public void testGetVanDetails_VanNotFound() {
        // Arrange
        Long vanId = 1L;
        when(mockVanRepository.findById(vanId)).thenReturn(Optional.empty());

        assertThrows(ChangeSetPersister.NotFoundException.class, () -> {
            toTest.getVanDetails(vanId);
        });

    }


    @Test
    public void testDeleteOffer() {
        // Arrange
        Long vanID = 1L;

        Van van1 = new Van();
        Van van2 = new Van();
        Van van3 = new Van();
        List<Van> mockCars = Arrays.asList(van1, van2, van3);
        van1.setId(1L);
        van2.setId(2L);
        van3.setId(3L);

        // Act
        toTest.deleteOffer(van1.getId());


        // Assert
        verify(mockVanRepository, times(1)).deleteById(vanID);
    }

}
