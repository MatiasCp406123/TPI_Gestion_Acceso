package com.example.Gestion_Acceso.services;

import com.example.Gestion_Acceso.dtos.NewVisitorDto;
import com.example.Gestion_Acceso.models.Visitors;

public interface VisitorService {
    Visitors crateVisitor(NewVisitorDto newVisitorDto);
}
