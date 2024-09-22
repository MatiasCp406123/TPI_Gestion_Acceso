package com.example.Gestion_Acceso.models;

import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserAllowedType {
    private Long id;
    private String description;
    private Integer crated_user;
    private LocalDateTime createdDate;
    private Integer lastUpdateUser;
    private LocalDateTime lastUpdateDate;
}
