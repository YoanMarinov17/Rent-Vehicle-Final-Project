package org.softuni.Rent_Vehicle_Company.model.entity;


import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@MappedSuperclass
@Getter
@Setter
public class BaseEntity {


    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private long id;

    public BaseEntity() {}
}
