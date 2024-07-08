package org.softuni.Rent_Vehicle_Company.model.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.softuni.Rent_Vehicle_Company.model.enums.EngineEnum;
import org.softuni.Rent_Vehicle_Company.model.enums.TypeEnum;

@Entity
@Table(name = "trucks")
@Getter
@Setter
@NoArgsConstructor
public class Truck extends Vehicle{


    @Column(name = "up_to_tones")
            private Integer upTones;

    public Truck(TypeEnum type, String model, int year, EngineEnum engine, Integer upTones) {
        super(TypeEnum.TRUCK, model, year, engine);
        this.upTones = upTones;
    }

    public Truck(Integer upTones) {
        this.upTones = upTones;
    }
}
