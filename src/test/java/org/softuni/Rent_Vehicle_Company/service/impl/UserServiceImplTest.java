package org.softuni.Rent_Vehicle_Company.service.impl;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.softuni.Rent_Vehicle_Company.model.dto.UserRegisterDto;
import org.softuni.Rent_Vehicle_Company.model.entity.*;
import org.softuni.Rent_Vehicle_Company.model.enums.UserRoleEnum;
import org.softuni.Rent_Vehicle_Company.repository.RoleRepository;
import org.softuni.Rent_Vehicle_Company.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;


import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.softuni.Rent_Vehicle_Company.model.enums.UserRoleEnum.ADMIN;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

    @Mock
    private PasswordEncoder mockPasswordEncoder;
    @Captor
    private ArgumentCaptor<User> userCaptor;
    @Mock
    private UserRepository mockUserRepository;
    private UserServiceImpl toTest;
    @Mock
    private RoleRepository mockRoleRepository;


    @BeforeEach
    void setUp() {
        toTest = new UserServiceImpl(
                mockPasswordEncoder,
                mockUserRepository,
                new ModelMapper(),
                mockRoleRepository);}


    @Test
    public void testRegister_FirstUser() {
        UserRegisterDto regDto = new UserRegisterDto();
        regDto.setUsername("yoan");
        regDto.setEmail("yoan@mail");
        regDto.setGender("male");
        regDto.setPassword("1234");

        Role adminRole = new Role();

        when(mockPasswordEncoder.encode(regDto.getPassword()))
                .thenReturn(regDto.getPassword()+regDto.getPassword());

        when(mockRoleRepository.findByRole(UserRoleEnum.ADMIN)).thenReturn(adminRole);

        toTest.register(regDto);

        verify(mockUserRepository).save(userCaptor.capture());
        User actualSavedEntity = userCaptor.getValue();

        assertNotNull(actualSavedEntity);
        Assertions.assertEquals(regDto.getUsername(), actualSavedEntity.getUsername());
        Assertions.assertEquals(regDto.getEmail(), actualSavedEntity.getEmail());
        Assertions.assertEquals(regDto.getPassword() + regDto.getPassword(), actualSavedEntity.getPassword());
        verify(mockRoleRepository).findByRole(UserRoleEnum.ADMIN);
        assertEquals(List.of(adminRole), actualSavedEntity.getRoles());
    }

    @Test
    void register_whenUsers_shouldAssignModeratorRole() {
        UserRegisterDto regDto = new UserRegisterDto();
        regDto.setUsername("yoan");
        regDto.setEmail("yoan@mail");
        regDto.setGender("male");
        regDto.setPassword("1234");
        Role moderatorRole = new Role();

        when(mockUserRepository.count()).thenReturn(1L);
        when(mockRoleRepository.findByRole(UserRoleEnum.MODERATOR)).thenReturn(moderatorRole);

        // Act
        toTest.register(regDto);

        verify(mockUserRepository).save(userCaptor.capture());
        User actualSavedEntity = userCaptor.getValue();

        // Assert
        verify(mockRoleRepository).findByRole(UserRoleEnum.MODERATOR);
        assertEquals(List.of(moderatorRole), actualSavedEntity.getRoles());
    }
    @Test
    void register_whenUsers_shouldAssignUserRole() {
        UserRegisterDto regDto = new UserRegisterDto();
        regDto.setUsername("yoan");
        regDto.setEmail("yoan@mail");
        regDto.setGender("male");
        regDto.setPassword("1234");
        Role userRole = new Role();

        when(mockUserRepository.count()).thenReturn(3L);
        when(mockRoleRepository.findByRole(UserRoleEnum.USER)).thenReturn(userRole);

        // Act
        toTest.register(regDto);

        verify(mockUserRepository).save(userCaptor.capture());
        User actualSavedEntity = userCaptor.getValue();

        // Assert
        verify(mockRoleRepository).findByRole(UserRoleEnum.USER);
        assertEquals(List.of(userRole), actualSavedEntity.getRoles());
    }



    @Test
    public void testUserExists_UserExists() {
        // Arrange
        String username = "existingUser";
        when(mockUserRepository.findByUsername(username)).thenReturn(Optional.of(new User()));

        // Act
        boolean result = toTest.userExist(username);

        // Assert
        assertTrue(result, "Expected userExist to return true when the user exists");
    }





    @Test
    public void testUserExists_UserDoesNotExist() {
        // Arrange
        String username = "nonExistingUser";
        when(mockUserRepository.findByUsername(username)).thenReturn(Optional.empty());

        // Act
        boolean result = toTest.userExist(username);

        // Assert
        assertFalse(result, "Expected userExist to return false when the user does not exist");
    }

    @Test
    public void testEmailExists_EmailExists() {
        // Arrange
        String email = "existing@example.com";
        when(mockUserRepository.findByEmail(email)).thenReturn(Optional.of(new User()));

        // Act
        boolean result = toTest.emailExist(email);

        // Assert
        assertTrue(result, "Expected emailExist to return true when the email exists");
    }

    @Test
    public void testEmailExists_EmailDoesNotExist() {
        // Arrange
        String email = "nonexisting@example.com";
        when(mockUserRepository.findByEmail(email)).thenReturn(Optional.empty());

        // Act
        boolean result = toTest.emailExist(email);

        // Assert
        assertFalse(result, "Expected emailExist to return false when the email does not exist");
    }


    @Test
    public void testFindAllRolesByUser() {
        // Arrange
        User user1 = new User();
        User user2 = new User();

        Vehicle vehicle1 = new Car();
        Vehicle vehicle2 = new Truck();
        Role role1 = new Role();
        Role role2 = new Role();
        role1.setRole(ADMIN);
        role2.setRole(UserRoleEnum.MODERATOR);

        user1.setVehicles(List.of(vehicle1, vehicle2));
        user1.setRoles(List.of(role1, role2));

        user2.setVehicles(List.of(vehicle1));
        user2.setRoles(List.of(role2));


        when(mockUserRepository.findAll()).thenReturn(Arrays.asList(user1, user2));

        // Act
        Map<User, Map<List<Vehicle>, List<Role>>> result = toTest.findAllRolesByUser();

        // Assert
        assertNotNull(result, "Result should not be null");
        assertEquals(2, result.size(), "The map should contain two users");

        // Check user1
        Map<List<Vehicle>, List<Role>> user1Map = result.get(user1);
        assertNotNull(user1Map, "Map for user1 should not be null");
        assertEquals(1, user1Map.size(), "User1's map should contain one entry");
        List<Vehicle> vehicles1 = new ArrayList<>(user1Map.keySet().iterator().next());
        List<Role> roles1 = user1Map.get(vehicles1);
        assertTrue(vehicles1.containsAll(Arrays.asList(vehicle1, vehicle2)), "User1 should have two vehicles");
        assertTrue(roles1.containsAll(Arrays.asList(role1, role2)), "User1 should have two roles");

        // Check user2
        Map<List<Vehicle>, List<Role>> user2Map = result.get(user2);
        assertNotNull(user2Map, "Map for user2 should not be null");
        assertEquals(1, user2Map.size(), "User2's map should contain one entry");
        List<Vehicle> vehicles2 = new ArrayList<>(user2Map.keySet().iterator().next());
        List<Role> roles2 = user2Map.get(vehicles2);
        assertTrue(vehicles2.contains(vehicle1), "User2 should have one vehicle");
        assertTrue(roles2.contains(role2), "User2 should have one role");
    }

    @Test
    public void testGetCurrentUser_UserExists() {
        // Arrange
        Long userId = 1L;
        User user = new User();
        user.setId(userId);

        when(mockUserRepository.findById(userId)).thenReturn(Optional.of(user));

        // Act
        User result = toTest.getCurrentUser(userId);

        // Assert
        assertNotNull(result, "User should not be null");
        assertEquals(userId, result.getId(), "User ID should match");
    }

    @Test
    public void testGetCurrentUser_UserDoesNotExist() {
        // Arrange
        Long userId = 1L;

        when(mockUserRepository.findById(userId)).thenReturn(Optional.empty());

        // Act & Assert
        Exception exception = assertThrows(NoSuchElementException.class, () -> {
            toTest.getCurrentUser(userId);
        });

        assertEquals("No value present", exception.getMessage(), "Exception message should match");
    }

    @Test
    public void testDeleteCurrentUser() {
        // Arrange
        Long userId = 1L;

        // Act
        toTest.deleteCurrentUser(userId);

        // Assert
        verify(mockUserRepository, times(1)).deleteById(userId);
    }


}
