package com.example.Gestion_Acceso.services;

import com.example.Gestion_Acceso.dtos.Allowed_DaysDto;
import com.example.Gestion_Acceso.models.AllowedDay;

import java.util.List;

public interface AllowedDaysService {
    List<AllowedDay> CreateAllowedDays(List<Allowed_DaysDto>allowedDaysDtos,Long id);
}
