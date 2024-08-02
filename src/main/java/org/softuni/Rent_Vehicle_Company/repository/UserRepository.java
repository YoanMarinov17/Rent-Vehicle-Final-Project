package org.softuni.Rent_Vehicle_Company.repository;


import org.softuni.Rent_Vehicle_Company.model.entity.Role;
import org.softuni.Rent_Vehicle_Company.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {


    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);

    void deleteById(Long id);

}
