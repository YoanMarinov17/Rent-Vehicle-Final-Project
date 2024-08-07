package org.softuni.Rent_Vehicle_Company.service.impl;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.*;

import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.softuni.Rent_Vehicle_Company.model.dto.CarDto;
import org.softuni.Rent_Vehicle_Company.model.dto.TruckDto;
import org.softuni.Rent_Vehicle_Company.model.dto.VanDto;
import org.softuni.Rent_Vehicle_Company.model.entity.*;

import org.softuni.Rent_Vehicle_Company.model.enums.EngineEnum;
import org.softuni.Rent_Vehicle_Company.model.enums.TypeCar;
import org.softuni.Rent_Vehicle_Company.model.enums.TypeEnum;
import org.softuni.Rent_Vehicle_Company.model.enums.UserRoleEnum;
import org.softuni.Rent_Vehicle_Company.repository.ReservationRepository;
import org.softuni.Rent_Vehicle_Company.repository.UserRepository;
import org.softuni.Rent_Vehicle_Company.repository.VehicleRepository;
import org.springframework.data.crossstore.ChangeSetPersister;

import java.security.Principal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class VehicleServiceImplTest {


    @InjectMocks
    private VehicleServiceImpl toTest;


    @Mock
    private VehicleRepository vehicleRepository;

    @Mock
    private ReservationRepository reservationRepository;

    @Mock
    private UserRepository userRepository;

    @Captor
    private ArgumentCaptor<Car> carCapture;

    @Captor
    private ArgumentCaptor<Van> vanCapture;


    @Captor
    private ArgumentCaptor<Truck> truckCapture;

    @BeforeEach
    void setUp() {
        toTest = new VehicleServiceImpl(vehicleRepository, new ModelMapper(), userRepository,reservationRepository );

    }


    @Test
    public void testCreateCar_UserExists() {
        // Arrange
        Principal principal = mock(Principal.class);
        when(principal.getName()).thenReturn("username");

        User user = new User();
        user.setId(1L);
        user.setUsername(principal.getName());

        CarDto carDto = new CarDto();
        carDto.setCarType(TypeCar.PREMIUM);
        carDto.setHorsePower(150);
        carDto.setNumberOfDoors(4);
        carDto.setNumberOfSeats(5);
        carDto.setPricePerDay(50);
        carDto.setImageUrl("asd");
        carDto.setBrand("Audi");
        carDto.setType(TypeEnum.CAR);
        carDto.setModel("A3");
        carDto.setYear(2020);
        carDto.setEngine(EngineEnum.PETROL);
        carDto.setUserId(user.getId());

        when(userRepository.findByUsername("username")).thenReturn(Optional.of(user));

        // Act
        toTest.createCar(carDto, principal);


        verify(vehicleRepository).save(carCapture.capture());

        Car actualSavedEntity = carCapture.getValue();


        Assertions.assertNotNull(actualSavedEntity);
        Assertions.assertEquals(carDto.getBrand(), actualSavedEntity.getBrand());
        Assertions.assertEquals(carDto.getUserId(), actualSavedEntity.getUser().getId());
        Assertions.assertEquals(carDto.getModel(), actualSavedEntity.getModel());
        Assertions.assertEquals(carDto.getCarType(), actualSavedEntity.getCarType());
        Assertions.assertEquals(carDto.getHorsePower(), actualSavedEntity.getHorsePower());
        Assertions.assertEquals(carDto.getYear(), actualSavedEntity.getYear());
        Assertions.assertEquals(carDto.getNumberOfDoors(), actualSavedEntity.getNumberOfDoors());
        Assertions.assertEquals(carDto.getNumberOfSeats(), actualSavedEntity.getNumberOfSeats());
        Assertions.assertEquals(carDto.getEngine(), actualSavedEntity.getEngine());
    }


    @Test
    public void testCreateCar_UserNotFound() {
        // Arrange
        CarDto carDto = new CarDto();
        Principal principal = mock(Principal.class);

        when(principal.getName()).thenReturn("username");
        when(userRepository.findByUsername("username")).thenReturn(Optional.empty());

        // Act
        toTest.createCar(carDto, principal);

        // Assert
        verify(userRepository, times(1)).findByUsername("username");
        verify(vehicleRepository, never()).save(any());
    }


    @Test
    public void testCreateVan() {
        Principal principal = mock(Principal.class);
        when(principal.getName()).thenReturn("username");

        User user = new User();
        user.setId(1L);
        user.setUsername(principal.getName());

        VanDto vanDto = new VanDto();
        vanDto.setNumberOfVanSeats(8);
        vanDto.setPricePerDay(50);
        vanDto.setImageUrl("asd");
        vanDto.setBrand("Mercedes");
        vanDto.setType(TypeEnum.VAN);
        vanDto.setModel("Vito");
        vanDto.setYear(2020);
        vanDto.setEngine(EngineEnum.PETROL);
        vanDto.setUserId(user.getId());

        when(userRepository.findByUsername("username")).thenReturn(Optional.of(user));

        // Act
        toTest.createVan(vanDto, principal);

        //Assert
        verify(vehicleRepository).save(vanCapture.capture());

        Van actualSavedEntity = vanCapture.getValue();

        Assertions.assertNotNull(actualSavedEntity);
        Assertions.assertEquals(vanDto.getBrand(), actualSavedEntity.getBrand());
        Assertions.assertEquals(vanDto.getUserId(), actualSavedEntity.getUser().getId());
        Assertions.assertEquals(vanDto.getModel(), actualSavedEntity.getModel());
        Assertions.assertEquals(vanDto.getYear(), actualSavedEntity.getYear());
        Assertions.assertEquals(vanDto.getNumberOfVanSeats(), actualSavedEntity.getNumberOfVanSeats());
        Assertions.assertEquals(vanDto.getEngine(), actualSavedEntity.getEngine());
    }

    @Test
    public void testCreateVan_UserNotFound() {
        // Arrange
        VanDto vanDto = new VanDto();
        Principal principal = mock(Principal.class);

        when(principal.getName()).thenReturn("username");
        when(userRepository.findByUsername("username")).thenReturn(Optional.empty());

        // Act
        toTest.createVan(vanDto, principal);

        // Assert
        verify(userRepository, times(1)).findByUsername("username");
        verify(vehicleRepository, never()).save(any());
    }


    @Test
    public void testCreateTruck() {
        Principal principal = mock(Principal.class);
        when(principal.getName()).thenReturn("username");

        User user = new User();
        user.setId(1L);
        user.setUsername(principal.getName());

        TruckDto truckDto = new TruckDto();
        truckDto.setUpTones("12.5");
        truckDto.setPricePerDay(50);
        truckDto.setImageUrl("asd");
        truckDto.setBrand("Mercedes");
        truckDto.setType(TypeEnum.TRUCK);
        truckDto.setModel("GTP");
        truckDto.setYear(2020);
        truckDto.setEngine(EngineEnum.PETROL);
        truckDto.setUserId(user.getId());

        when(userRepository.findByUsername("username")).thenReturn(Optional.of(user));

        // Act
        toTest.createTruck(truckDto, principal);


        verify(vehicleRepository).save(truckCapture.capture());

        Truck actualSavedEntity = truckCapture.getValue();


        Assertions.assertNotNull(actualSavedEntity);
        Assertions.assertEquals(truckDto.getBrand(), actualSavedEntity.getBrand());
        Assertions.assertEquals(truckDto.getUserId(), actualSavedEntity.getUser().getId());
        Assertions.assertEquals(truckDto.getModel(), actualSavedEntity.getModel());
        Assertions.assertEquals(truckDto.getYear(), actualSavedEntity.getYear());
        Assertions.assertEquals(truckDto.getUpTones(), actualSavedEntity.getUpTones());
        Assertions.assertEquals(truckDto.getEngine(), actualSavedEntity.getEngine());
    }

    @Test
    public void testCreateTruck_UserNotFound() {
        // Arrange
        TruckDto truckDto = new TruckDto();
        Principal principal = mock(Principal.class);

        when(principal.getName()).thenReturn("username");
        when(userRepository.findByUsername("username")).thenReturn(Optional.empty());

        // Act
        toTest.createTruck(truckDto, principal);

        // Assert
        verify(userRepository, times(1)).findByUsername("username");

        verify(vehicleRepository, never()).save(any());
    }


    @Test
    public void testGetAllVehiclesByUser() {
        // Arrange
        Principal principal = mock(Principal.class);
        User user = new User();
        user.setId(1L);

        Vehicle vehicle1 = new Car();
        vehicle1.setUser(user);

        Vehicle vehicle2 = new Van();
        vehicle2.setUser(user);

        Vehicle vehicle3 = new Van();
        vehicle3.setUser(new User()); // Different user

        List<Vehicle> allVehicles = List.of(vehicle1, vehicle2, vehicle3);

        when(principal.getName()).thenReturn("username");
        when(userRepository.findByUsername("username")).thenReturn(Optional.of(user));
        when(vehicleRepository.findAll()).thenReturn(allVehicles);

        // Act
        List<Vehicle> result = toTest.getAllVehiclesByUser(principal);

        // Assert
        assertEquals(2, result.size()); // Only two vehicles should belong to the user
        assertEquals(vehicle1, result.get(0));
        assertEquals(vehicle2, result.get(1));
    }

    @Test
    public void testGetAllVehiclesByUser_UserNotFound() {
        // Arrange
        Principal principal = mock(Principal.class);

        when(principal.getName()).thenReturn("username");
        when(userRepository.findByUsername("username")).thenReturn(Optional.empty());

        // Act
        List<Vehicle> result = toTest.getAllVehiclesByUser(principal);

        // Assert
        assertEquals(0, result.size()); // No vehicles should be returned if user is not found
        verify(vehicleRepository, never()).findAll(); // Ensure findAll is not called
    }


    @Test
    public void testGetVehicleDetails_Found() throws ChangeSetPersister.NotFoundException {
        // Arrange
        Long vehicleId = 1L;
        Car car = new Car(); // Use concrete implementation
        car.setId(vehicleId);

        when(vehicleRepository.findById(vehicleId)).thenReturn(Optional.of(car));

        // Act
        Vehicle result = toTest.getVehicleDetails(vehicleId);

        // Assert
        assertEquals(car, result);
    }

    @Test
    public void testGetVehicleDetails_NotFound() {
        // Arrange
        Long vehicleId = 1L;

        when(vehicleRepository.findById(vehicleId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(ChangeSetPersister.NotFoundException.class, () -> {
            toTest.getVehicleDetails(vehicleId);
        });
    }

    @Test
    public void testDeleteOffer() {
        // Arrange
        Long vehicleId = 1L;

        Vehicle car = new Car();
        Vehicle van = new Van();
        Vehicle truck = new Truck();
        List<Vehicle> mockVehicles = Arrays.asList(car, van, truck);
        car.setId(1L);
        van.setId(2L);
        truck.setId(3L);

        // Act
        toTest.deleteOffer(car.getId());


        // Assert
        verify(vehicleRepository, times(1)).deleteById(car.getId());
    }


}



