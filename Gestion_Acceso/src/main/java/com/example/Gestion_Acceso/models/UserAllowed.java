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
    private UserAllowedType userType;
    private DocumentType documentType;
    private Long createdUser;
    private LocalDateTime createdDate;
    private Long lastUpdatedUser;
    private LocalDateTime lastUpdatedDate;
    private String email;
    private boolean emailSent;
    private List<AuthRange> authRanges;
    private List<Vehicle> vehicles;
}
