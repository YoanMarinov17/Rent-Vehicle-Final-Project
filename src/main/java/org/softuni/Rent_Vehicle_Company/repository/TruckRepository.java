package org.softuni.Rent_Vehicle_Company.repository;


import org.softuni.Rent_Vehicle_Company.model.entity.Truck;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TruckRepository extends JpaRepository<Truck, Long> {
}
