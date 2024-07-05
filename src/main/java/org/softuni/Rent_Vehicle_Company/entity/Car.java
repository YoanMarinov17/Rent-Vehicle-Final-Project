package org.softuni.Rent_Vehicle_Company.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name = "cars")
public class Car extends BaseEntity{




}
