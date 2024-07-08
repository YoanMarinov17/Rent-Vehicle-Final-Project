package org.softuni.Rent_Vehicle_Company.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.softuni.Rent_Vehicle_Company.entity.enums.EngineEnum;
import org.softuni.Rent_Vehicle_Company.entity.enums.TypeEnum;

@Entity
@Table(name = "trucks")
@Getter
@Setter
@NoArgsConstructor
public class Truck extends Vehicle{


    @Column(name = "up_to_tones")
            private String upTones;

    public Truck(TypeEnum type, String model, int year, EngineEnum engine, String upTones) {
        super(TypeEnum.TRUCK, model, year, engine);
        this.upTones = upTones;
    }

    public Truck(String upTones) {
        this.upTones = upTones;
    }
}
