package org.softuni.Rent_Vehicle_Company.service.impl;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.softuni.Rent_Vehicle_Company.model.dto.ReservationDto;
import org.softuni.Rent_Vehicle_Company.model.entity.*;
import org.softuni.Rent_Vehicle_Company.model.enums.StatusEnum;
import org.softuni.Rent_Vehicle_Company.repository.ReservationRepository;
import org.softuni.Rent_Vehicle_Company.repository.UserRepository;
import org.softuni.Rent_Vehicle_Company.repository.VehicleRepository;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ReservationServiceImplTest {

    private ReservationServiceImpl toTest;

    @Mock
    private UserRepository mockUserRepository;


    @Mock
    private VehicleRepository vehicleRepository;

    @Captor
    private ArgumentCaptor<Reservation> reservationCaptor;

    @Mock
    private ReservationRepository reservationRepository;

    @BeforeEach
    void setUp() {
        toTest = new ReservationServiceImpl(mockUserRepository, new ModelMapper(), vehicleRepository,reservationRepository);

    }


    @Test
    public void testCreateReservation() {

        Principal principal = mock(Principal.class);
        when(principal.getName()).thenReturn("username");

        User user = new User();
        user.setId(1L);
        user.setUsername(principal.getName());

        Car car = new Car();
        car.setId(1L);
        String username = "testUser";
        ReservationDto resDto = new ReservationDto();
        resDto.setFromDate("2024-08-10");
        resDto.setEndDate("2024-08-10");
        resDto.setPickUpTime("10:00");
        resDto.setStatus(StatusEnum.PENDING);
        resDto.setCardCredentials("1234 1234 1235 1235");
        resDto.setLocation("Sofia");

        when(mockUserRepository.findByUsername("username")).thenReturn(Optional.of(user));

        when(this.vehicleRepository.findById(1L)).thenReturn(Optional.of(car));

        toTest.createReservation(resDto,principal, car.getId());

        verify(reservationRepository).save(reservationCaptor.capture());

        Reservation actualSavedEntity = reservationCaptor.getValue();

        Assertions.assertNotNull(actualSavedEntity);
        assertEquals(resDto.getStatus(), actualSavedEntity.getStatus());
        assertEquals(resDto.getLocation(), actualSavedEntity.getLocation());
        assertEquals(resDto.getFromDate(), actualSavedEntity.getFromDate().toString());
        assertEquals(resDto.getEndDate(), actualSavedEntity.getEndDate().toString());
        assertEquals(resDto.getCardCredentials(), actualSavedEntity.getCardCredentials());
        assertEquals(resDto.getPickUpTime(), actualSavedEntity.getPickUpTime().toString());
    }




    @Test
    void getAllPendingRequests_whenUserIsPresent_shouldReturnPendingReservations() {
        // Arrange

        Principal principal = mock(Principal.class);
        when(principal.getName()).thenReturn("username");

        User user = new User();
        user.setId(1L);
        user.setUsername(principal.getName());


        Reservation reservation1 = new Reservation();
        reservation1.setStatus(StatusEnum.PENDING);
        reservation1.setVehicle(new Car());
        reservation1.getVehicle().setUser(user);


        Reservation reservation2 = new Reservation();
        reservation2.setStatus(StatusEnum.APPROVED);// Different status
        reservation1.setVehicle(new Van());
        reservation1.getVehicle().setUser(user);


        List<Reservation> allReservations = List.of(reservation1, reservation2);

        when(mockUserRepository.findByUsername("username")).thenReturn(Optional.of(user));
        when(reservationRepository.findAll()).thenReturn(allReservations);


        // Act
        List<Reservation> result = toTest.getAllPendingRequests(principal);

        // Assert
        assertEquals(1, result.size());
        assertTrue(result.contains(reservation1));
        assertFalse(result.contains(reservation2));
    }

    @Test
    void findExpiredReservations_shouldReturnOnlyExpiredReservations() {
        // Arrange
        LocalDate today = LocalDate.now();

        Reservation reservation1 = new Reservation();
        reservation1.setEndDate(today.minusDays(1)); // Expired reservation

        Reservation reservation2 = new Reservation();
        reservation2.setEndDate(today.plusDays(1)); // Not expired reservation

        Reservation reservation3 = new Reservation();
        reservation3.setEndDate(null); // No end date

        List<Reservation> allReservations = List.of(reservation1, reservation2, reservation3);

        when(reservationRepository.findAll()).thenReturn(allReservations);

        // Act
        List<Reservation> result = toTest.findExpiredReservations();

        // Assert
        assertEquals(1, result.size());
        assertTrue(result.contains(reservation1));
        assertFalse(result.contains(reservation2));
        assertFalse(result.contains(reservation3));
    }

}
