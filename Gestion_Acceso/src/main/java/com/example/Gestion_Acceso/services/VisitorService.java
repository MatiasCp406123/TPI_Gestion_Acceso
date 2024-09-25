package com.example.Gestion_Acceso.services;

import com.example.Gestion_Acceso.dtos.NewVisitorDto;
import com.example.Gestion_Acceso.models.UserAllowed;

import java.util.List;

public interface VisitorService {
    List<UserAllowed> crateVisitor(List<NewVisitorDto> newVisitorDto);
}
