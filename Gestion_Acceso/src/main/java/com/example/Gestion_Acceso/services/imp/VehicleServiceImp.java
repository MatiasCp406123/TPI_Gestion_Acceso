package com.example.Gestion_Acceso.services.imp;

import com.example.Gestion_Acceso.dtos.NewVehicleDto;
import com.example.Gestion_Acceso.entities.VehiclesEntity;

import com.example.Gestion_Acceso.models.Vehicle;
import com.example.Gestion_Acceso.models.VehicleType;
import com.example.Gestion_Acceso.repositories.Types.Vehicle_TypeRepository;
import com.example.Gestion_Acceso.repositories.VehicleRepository;
import com.example.Gestion_Acceso.services.Users_AllowedService;
import com.example.Gestion_Acceso.services.VehicleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;

@Service
public class VehicleServiceImp implements VehicleService {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private Vehicle_TypeRepository vehicle_typeRepository;
    @Autowired
    private VehicleRepository vehicleRepository;
    @Autowired
    private Users_AllowedService users_allowedService;
    @Override
    public Vehicle createVehicle(NewVehicleDto newVehicleDto) {
        if(newVehicleDto==null){
            return null;
        }
        Date date=new Date();
        VehicleType vehicleType =modelMapper.map(vehicle_typeRepository.getByDescription(newVehicleDto.getVehicleTypeId().getDescription()), VehicleType.class);

        Vehicle vehicle=new Vehicle();
        vehicle.setVehicleTypeId(vehicleType);
        vehicle.setPlate(newVehicleDto.getPlate());
        vehicle.setInsurace(newVehicleDto.getInsurace());
        VehiclesEntity vehiclesEntity=modelMapper.map(vehicle,VehiclesEntity.class);

        vehiclesEntity.setCreatedUser(1);
        vehiclesEntity.setCreatedDate(date);
        Vehicle response=modelMapper.map(vehicleRepository.save(vehiclesEntity), Vehicle.class);
        return response;
    }
}
