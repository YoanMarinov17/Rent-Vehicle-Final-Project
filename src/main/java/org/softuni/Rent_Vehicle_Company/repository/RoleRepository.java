package org.softuni.Rent_Vehicle_Company.repository;

import org.softuni.Rent_Vehicle_Company.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(String name);
}
