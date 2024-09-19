package com.example.Gestion_Acceso.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class NewUserAllowedDto {
    private String document;
    private String name;
    private Document_TypeDto documentType;
    private User_allowedTypeDto user_allowed_Type;
    private String email;
}
