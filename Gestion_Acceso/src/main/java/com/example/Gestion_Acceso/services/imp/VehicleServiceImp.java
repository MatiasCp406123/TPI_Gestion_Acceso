package com.example.Gestion_Acceso.services.imp;

import com.example.Gestion_Acceso.dtos.NewVehicleDto;
import com.example.Gestion_Acceso.entities.Users_AllowedEntity;
import com.example.Gestion_Acceso.entities.VehiclesEntity;

import com.example.Gestion_Acceso.models.Vehicle;
import com.example.Gestion_Acceso.models.VehicleType;
import com.example.Gestion_Acceso.repositories.Types.Vehicle_TypeRepository;
import com.example.Gestion_Acceso.repositories.Users_AllowedRepository;
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
    private Users_AllowedRepository users_allowedRepository;
    @Override
    public Vehicle createVehicle(NewVehicleDto newVehicleDto,Long id) {
        if(newVehicleDto==null){
            return null;
        }
        Users_AllowedEntity users_allowedEntity=users_allowedRepository.findById(id).get();
        Date date=new Date();
        VehicleType vehicleType =modelMapper.map(vehicle_typeRepository.getByDescription(newVehicleDto.getVehicleTypeId().getDescription()), VehicleType.class);

        Vehicle vehicle=new Vehicle();
        vehicle.setVehicleType(vehicleType.getId());
        vehicle.setPlate(newVehicleDto.getPlate());
        vehicle.setInsurace(newVehicleDto.getInsurace());   
        VehiclesEntity vehiclesEntity=modelMapper.map(vehicle,VehiclesEntity.class);
        vehiclesEntity.setUsers_allowedId(users_allowedEntity);
        vehiclesEntity.setCreatedUser(1);
        vehiclesEntity.setCreatedDate(date);
        return modelMapper.map(vehicleRepository.save(vehiclesEntity), Vehicle.class);

    }
}
