package com.example.Gestion_Acceso.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserAllowed {
    private Long id;
    private String document;
    private String name;
    private Long userType;
    private Long documentType;
    private Integer created_user;
    private LocalDateTime created_date;
    private Integer last_updated_user;
    private LocalDateTime last_updated_date;
    private String email;
    private boolean emailSent;
    private List<AuthRange> authRanges;
    private List<Vehicle> vehicles;
}
