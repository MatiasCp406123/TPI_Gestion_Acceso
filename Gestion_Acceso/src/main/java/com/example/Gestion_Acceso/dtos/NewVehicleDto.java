package com.example.Gestion_Acceso.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class NewVehicleDto {
    private String plate;
    private VehicleTypeDto vehicleTypeDto;
    private String insurace;
}
