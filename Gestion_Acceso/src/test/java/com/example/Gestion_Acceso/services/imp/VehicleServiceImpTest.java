package com.example.Gestion_Acceso.services.imp;

import com.example.Gestion_Acceso.dtos.NewVehicleDto;
import com.example.Gestion_Acceso.dtos.VehicleTypeDto;
import com.example.Gestion_Acceso.entities.VehiclesEntity;
import com.example.Gestion_Acceso.entities.Vehicles_TypesEntity;
import com.example.Gestion_Acceso.models.Vehicle;
import com.example.Gestion_Acceso.repositories.Types.Vehicle_TypeRepository;
import com.example.Gestion_Acceso.repositories.VehicleRepository;
import com.example.Gestion_Acceso.services.VehicleService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class VehicleServiceImpTest {
    @SpyBean
    private VehicleService vehicleService;
    @MockBean
    private VehicleRepository vehicleRepository;
    @MockBean
    private Vehicle_TypeRepository vtRepository;
    @Test
    void createVehicle() {
        Vehicles_TypesEntity vehicles_typesEntity=new Vehicles_TypesEntity();
        vehicles_typesEntity.setId(1L);
        vehicles_typesEntity.setDescription("Truck");
        Mockito.when(vtRepository.getByDescription("Truck")).thenReturn(vehicles_typesEntity);

        VehiclesEntity vehiclesEntity=new VehiclesEntity();
        vehiclesEntity.setId(1);
        vehiclesEntity.setVehicleType(vehicles_typesEntity.getId());
        vehiclesEntity.setPlate("12ed3");
        vehiclesEntity.setInsurace("ok");
        Mockito.when(vehicleRepository.save(Mockito.any(VehiclesEntity.class))).thenReturn(vehiclesEntity);

        VehicleTypeDto vehicleTypeDto=new VehicleTypeDto();
        vehicleTypeDto.setDescription("Truck");

        NewVehicleDto newVehicleDto=new NewVehicleDto();
        newVehicleDto.setInsurace("ok");
        newVehicleDto.setVehicleTypeId(vehicleTypeDto);
        newVehicleDto.setPlate("12ed3");

        Vehicle vehicle=vehicleService.createVehicle(newVehicleDto,1L);
        Assertions.assertNotNull(vehicle);
        Assertions.assertEquals(1L,vehicle.getVehicleType());
    }
    @Test
    void createNull(){
        Vehicle vehicle=vehicleService.createVehicle(null,null);
        Assertions.assertNull(vehicle);
        Assertions.assertEquals(null,vehicle);
    }
}