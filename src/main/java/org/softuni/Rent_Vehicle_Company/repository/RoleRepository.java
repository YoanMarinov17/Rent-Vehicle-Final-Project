package org.softuni.Rent_Vehicle_Company.repository;

import org.softuni.Rent_Vehicle_Company.model.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(String name);
}
