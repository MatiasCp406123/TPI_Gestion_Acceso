package com.example.Gestion_Acceso.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Vehicle {
    private Long id;
    private VehicleType vehicleTypeId;
    private String plate;
    private String insurance;
}
