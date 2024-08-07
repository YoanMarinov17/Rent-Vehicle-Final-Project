package org.softuni.Rent_Vehicle_Company.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.softuni.Rent_Vehicle_Company.model.entity.Car;
import org.softuni.Rent_Vehicle_Company.model.enums.TypeCar;
import org.softuni.Rent_Vehicle_Company.model.enums.TypeEnum;
import org.softuni.Rent_Vehicle_Company.repository.CarRepository;
import org.softuni.Rent_Vehicle_Company.repository.VehicleRepository;
import org.springframework.data.crossstore.ChangeSetPersister;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class CarServiceImplTest {


    @InjectMocks
    private CarServiceImpl toTest;


    @Mock
    private CarRepository mockCarRepository;


    @Mock
    private VehicleRepository mockVehicleRepository;


    @BeforeEach
    void setUp() {
        toTest = new CarServiceImpl(mockCarRepository, mockVehicleRepository);
    }


    @Test
    void getAllCarSummaryTest() {

        Car car1 = new Car();
        Car car2 = new Car();
        Car car3 = new Car();
        List<Car> mockCars = Arrays.asList(car1, car2, car3);

        Mockito.when(mockCarRepository.findAll())
                .thenReturn(mockCars);


        List<Car> allCars = toTest.getAllCarSummary();


        assertNotNull(allCars);
        assertEquals(3, allCars.size());
    }


    @Test
    void getCarDetailsTest() throws ChangeSetPersister.NotFoundException {
        Car car = new Car();
        car.setId(1L);
        car.setCarType(TypeCar.PREMIUM);
        car.setType(TypeEnum.CAR);
        car.setYear(2020);
        car.setModel("X5");
        car.setBrand("BMW");
        car.setPricePerDay(500);
        car.setHorsePower(200);
        car.setNumberOfDoors(4);
        car.setNumberOfSeats(5);
        car.setIsReserved(false);

        Mockito.when(mockCarRepository.findById(1L)).thenReturn(Optional.of(car));

        Car result = toTest.getCarDetails(car.getId());

        assertEquals(1L, result.getId());
        assertEquals(TypeCar.PREMIUM, result.getCarType());
        assertEquals(TypeEnum.CAR, result.getType());
        assertEquals(2020, result.getYear());
        assertEquals("X5", result.getModel());
        assertEquals("BMW", result.getBrand());
        assertEquals(500, result.getPricePerDay());
        assertEquals(200, result.getHorsePower());
        assertEquals(4, result.getNumberOfDoors());
        assertEquals(5, result.getNumberOfSeats());
        assertEquals(false, result.getIsReserved());
    }

    @Test
    public void testGetCarDetails_CarNotFound() {
        // Arrange
        Long carId = 1L;
        when(mockCarRepository.findById(carId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(ChangeSetPersister.NotFoundException.class, () -> {
            toTest.getCarDetails(carId);
        });

    }


    @Test
    public void testDeleteOffer() {
        // Arrange
        Long carId = 1L;

        Car car1 = new Car();
        Car car2 = new Car();
        Car car3 = new Car();
        List<Car> mockCars = Arrays.asList(car1, car2, car3);
        car1.setId(1L);
        car2.setId(2L);
        car3.setId(3L);

        // Act
        toTest.deleteOffer(car1.getId());


        // Assert
        verify(mockCarRepository, times(1)).deleteById(carId);
    }
}
