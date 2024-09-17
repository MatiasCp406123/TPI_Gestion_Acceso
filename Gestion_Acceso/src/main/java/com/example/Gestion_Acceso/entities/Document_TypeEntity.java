package com.example.Gestion_Acceso.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@Entity
@Table(name = "document_type")
@AllArgsConstructor
@NoArgsConstructor
public class Document_TypeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private Integer createdUser;
    private LocalDateTime createdDate;
    private Integer lastUpdatedUser;
    private LocalDateTime lastUpdatedDate;
}
