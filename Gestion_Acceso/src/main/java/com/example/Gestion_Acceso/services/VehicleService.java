package com.example.Gestion_Acceso.services;

import com.example.Gestion_Acceso.dtos.NewVehicleDto;
import com.example.Gestion_Acceso.models.Vehicle;

public interface VehicleService {
    Vehicle createVehicle(NewVehicleDto newVehicleDto,Long id);
}
