package org.softuni.Rent_Vehicle_Company.service.impl;


import org.modelmapper.ModelMapper;
import org.softuni.Rent_Vehicle_Company.model.dto.CarDto;
import org.softuni.Rent_Vehicle_Company.model.dto.TruckDto;
import org.softuni.Rent_Vehicle_Company.model.dto.VehicleDto;
import org.softuni.Rent_Vehicle_Company.model.entity.Cars.Car;
import org.softuni.Rent_Vehicle_Company.model.entity.Cars.ElectricCar;
import org.softuni.Rent_Vehicle_Company.model.entity.Cars.NormalCar;
import org.softuni.Rent_Vehicle_Company.model.entity.Cars.PremiumCar;
import org.softuni.Rent_Vehicle_Company.model.entity.Truck;
import org.softuni.Rent_Vehicle_Company.model.entity.Van;
import org.softuni.Rent_Vehicle_Company.model.entity.Vehicle;
import org.softuni.Rent_Vehicle_Company.model.enums.EngineEnum;
import org.softuni.Rent_Vehicle_Company.model.enums.TypeEnum;
import org.softuni.Rent_Vehicle_Company.repository.TruckRepository;
import org.softuni.Rent_Vehicle_Company.repository.VehicleRepository;
import org.softuni.Rent_Vehicle_Company.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Service
public class VehicleServiceImpl implements VehicleService {

   private final VehicleRepository vehicleRepository;
   private final ModelMapper modelMapper;



   private final TruckRepository truckRepository;

    public VehicleServiceImpl(VehicleRepository vehicleRepository, ModelMapper modelMapper, TruckRepository truckRepository) {
        this.vehicleRepository = vehicleRepository;
        this.modelMapper = modelMapper;
        this.truckRepository = truckRepository;
    }

    @Override
    public void createVehicle(VehicleDto vehicleDto) {

        switch (vehicleDto.getType()){
//            case CAR:
//            Car car = new Car();
//            modelMapper.map(vehicleDto, Car.class);
//            vehicleRepository.save(car);

//            switch (car.getCarType()){
//
//                case NORMAL:
//                    NormalCar normalCar = new NormalCar();
//                    normalCar.setAirConditioner(vehicleDto.isHasAirConditioner());
//
//                case PREMIUM:
//
//                    PremiumCar premiumCar = new PremiumCar();
//                    premiumCar.setTypePremiumCar(vehicleDto.getPremiumType());
//
//                case ELECTRIC:
//                    ElectricCar electricCar = new ElectricCar();
//                    electricCar.setBatteryLifeInKm(vehicleDto.getBatteryLife());
//            }

//            break;
//
//            case VAN:
//                Van van = new Van();
//                van.setNumberOfSeats(vehicleDto.getNumberSeats());
//                break;
//
            case TRUCK:


                Truck truck = new Truck();
                truck.setType(vehicleDto.getType());
                truck.setModel(vehicleDto.getModel());
                truck.setYear(vehicleDto.getYear());
                truck.setEngine(vehicleDto.getEngine());

                truckRepository.save(truck);

                break;
        }

//        Vehicle vehicle = new Vehicle() {};
//
//        vehicle.setType(vehicleDto.getType());
//        vehicle.setModel(vehicleDto.getModel());
//        vehicle.setYear(vehicleDto.getYear());
//        vehicle.setEngine(vehicleDto.getEngine());


    }
}
