package com.example.Gestion_Acceso.models;

import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserAllowedType {
    private Long id;
    private String description;
    private Long createdUser;
    private LocalDateTime createdDate;
    private Long lastUpdatedUser;
    private LocalDateTime lastUpdatedDate;
}
