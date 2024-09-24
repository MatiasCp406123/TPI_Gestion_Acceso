package com.example.Gestion_Acceso.services.imp;

import com.example.Gestion_Acceso.dtos.NewVehicleDto;
import com.example.Gestion_Acceso.dtos.VehicleTypeDto;
import com.example.Gestion_Acceso.entities.VehiclesEntity;
import com.example.Gestion_Acceso.entities.Vehicles_TypesEntity;
import com.example.Gestion_Acceso.models.Vehicle;
import com.example.Gestion_Acceso.models.VehicleType;
import com.example.Gestion_Acceso.repositories.Types.Vehicle_TypeRepository;
import com.example.Gestion_Acceso.repositories.VehicleRepository;
import com.example.Gestion_Acceso.services.VehicleService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class VehicleServiceImpTest {
    @SpyBean
    private VehicleService vehicleService;
    @MockBean
    private VehicleRepository vehicleRepository;
    @MockBean
    private Vehicle_TypeRepository vtRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Test
    void createVehicle() {

        Vehicles_TypesEntity vehicles_typesEntity = new Vehicles_TypesEntity();
        vehicles_typesEntity.setId(1);
        vehicles_typesEntity.setDescription("Truck");
        Mockito.when(vtRepository.getByDescription("Truck")).thenReturn(vehicles_typesEntity);

        VehiclesEntity savedVehiclesEntity = new VehiclesEntity();
        savedVehiclesEntity.setId(1);
        savedVehiclesEntity.setVehicleType(vehicles_typesEntity.getId().longValue());
        savedVehiclesEntity.setPlate("12ed3");
        savedVehiclesEntity.setInsurace("ok");
        Mockito.when(vehicleRepository.save(Mockito.any(VehiclesEntity.class))).thenReturn(savedVehiclesEntity);


        VehicleTypeDto vehicleTypeDto = new VehicleTypeDto();
        vehicleTypeDto.setDescription("Truck");

        NewVehicleDto vehicleDto = new NewVehicleDto();
        vehicleDto.setInsurace("ok");
        vehicleDto.setVehicleTypeId(vehicleTypeDto);
        vehicleDto.setPlate("12ed3");


        Vehicle createdVehicle = vehicleService.createVehicle(vehicleDto);

        Assertions.assertNotNull(createdVehicle);
        Assertions.assertEquals(1L, createdVehicle.getVehicleType());
        Assertions.assertEquals("12ed3", createdVehicle.getPlate());
        Assertions.assertEquals("ok", createdVehicle.getInsurace());

        Mockito.verify(vtRepository).getByDescription("Truck");
        Mockito.verify(vehicleRepository).save(Mockito.any(VehiclesEntity.class));

    }
    @Test
    void createNull(){
        Vehicle vehicle=vehicleService.createVehicle(null);
        Assertions.assertNull(vehicle);
        Assertions.assertEquals(null,vehicle);
    }
}