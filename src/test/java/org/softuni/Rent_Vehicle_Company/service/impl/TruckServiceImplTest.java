package org.softuni.Rent_Vehicle_Company.service.impl;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.softuni.Rent_Vehicle_Company.model.entity.Car;
import org.softuni.Rent_Vehicle_Company.model.entity.Truck;
import org.softuni.Rent_Vehicle_Company.model.enums.TypeCar;
import org.softuni.Rent_Vehicle_Company.model.enums.TypeEnum;
import org.softuni.Rent_Vehicle_Company.repository.CarRepository;
import org.softuni.Rent_Vehicle_Company.repository.TruckRepository;
import org.softuni.Rent_Vehicle_Company.repository.VehicleRepository;
import org.springframework.data.crossstore.ChangeSetPersister;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TruckServiceImplTest {

    private TruckServiceImpl toTest;


    @Mock
    private TruckRepository mockTruckRepository;


    @Mock
    private VehicleRepository mockVehicleRepository;


    @BeforeEach
    void setUp() {
        toTest = new TruckServiceImpl(mockTruckRepository);
    }


    @Test
    void getAllCarSummaryTest() {

        Truck truck1 = new Truck();
        Truck truck2 = new Truck();
        Truck truck3 = new Truck();
        List<Truck> mockCars = Arrays.asList(truck1, truck2, truck3);

        Mockito.when(mockTruckRepository.findAll())
                .thenReturn(mockCars);


        List<Truck> allTrucks = toTest.getAllTrucksSummary();


        assertNotNull(allTrucks);
        assertEquals(3, allTrucks.size());
    }


    @Test
    void getTruckDetailsTest() throws ChangeSetPersister.NotFoundException {
        Truck truck = new Truck();
        truck.setId(1L);
      truck.setUpTones("2.5");
        truck.setType(TypeEnum.TRUCK);
        truck.setYear(2020);
        truck.setModel("X5");
        truck.setBrand("BMW");
        truck.setPricePerDay(500);
        truck.setIsReserved(false);

        Mockito.when(mockTruckRepository.findById(1L)).thenReturn(Optional.of(truck));

        Truck result = toTest.getTruckDetails(truck.getId());

        assertEquals(1L, result.getId());
         assertEquals("2.5", result.getUpTones());
        assertEquals(TypeEnum.TRUCK, result.getType());
        assertEquals(2020, result.getYear());
        assertEquals("X5", result.getModel());
        assertEquals("BMW", result.getBrand());
        assertEquals(500, result.getPricePerDay());
        assertEquals(false, result.getIsReserved());
    }


    @Test
    public void testGetTruckDetails_TruckNotFound() {
        // Arrange
        Long truckId = 1L;
        when(mockTruckRepository.findById(truckId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(ChangeSetPersister.NotFoundException.class, () -> {
            toTest.getTruckDetails(truckId);
        });

    }


    @Test
    public void testDeleteOffer() {
        // Arrange
        Long truckId = 1L;

        Truck truck1 = new Truck();
        Truck truck2 = new Truck();
        Truck truck3 = new Truck();
        List<Truck> mockTrucks = Arrays.asList(truck1, truck2, truck3);
        truck1.setId(1L);
        truck2.setId(2L);
        truck3.setId(3L);

        // Act
        toTest.deleteOffer(truck1.getId());


        // Assert
        verify(mockTruckRepository, times(1)).deleteById(truckId);
    }

}
