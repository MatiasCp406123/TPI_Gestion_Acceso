package com.example.Gestion_Acceso.services;

import com.example.Gestion_Acceso.dtos.NewUserAllowedDto;
import com.example.Gestion_Acceso.models.UserAllowed;
import org.springframework.stereotype.Service;


public interface Users_AllowedService {
    UserAllowed createUserAllowed(NewUserAllowedDto newUserAllowedDto);
}
