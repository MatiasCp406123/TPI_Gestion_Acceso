package com.example.Gestion_Acceso.services;

import com.example.Gestion_Acceso.models.UserAllowed;

import java.io.IOException;
import java.util.List;

public interface UserAllowedService {
    List<UserAllowed> getAllUsers() throws IOException;

}