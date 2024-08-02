package org.softuni.Rent_Vehicle_Company;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class RentVehicleCompanyApplication {

	public static void main(String[] args) {
		SpringApplication.run(RentVehicleCompanyApplication.class, args);
	}

}
