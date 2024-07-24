package org.softuni.Rent_Vehicle_Company.model.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
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
            private String upTones;

    @Column(name = "is_reserved")
    private Boolean isReserved;

    @Column(name = "price_per_day")
    private Integer pricePerDay;

    @Column(name = "image_url")
    @Size(min = 5, max = 1232132132)
    private String imageUrl;

}
