package com.example.Gestion_Acceso.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class NewVisitorDto {
    private NewAuthRangeDto newAuthRangeDto;
    private NewUserAllowedDto newUserAllowedDto;
    private NewVehicleDto newVehicleDto;
}
