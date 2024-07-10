package org.softuni.Rent_Vehicle_Company.repository;


import org.softuni.Rent_Vehicle_Company.model.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleRepository  extends JpaRepository<Vehicle, Long> {
}
