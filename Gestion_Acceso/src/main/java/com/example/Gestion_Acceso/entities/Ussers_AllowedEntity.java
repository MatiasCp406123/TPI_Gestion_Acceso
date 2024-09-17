package com.example.Gestion_Acceso.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users_allowed")
public class Ussers_AllowedEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String document;
    @ManyToOne
    @JoinColumn(name = "users_type_id")
    private Users_allowed_typesEntity userType;
    @ManyToOne
    @JoinColumn(name = "document_type_id")
    private Document_TypeEntity documentType;
    private Integer createdUser;
    private LocalDateTime createdDate;
    private Integer lastUpdatedUser;
    private LocalDateTime lastUpdatedDate;
}
