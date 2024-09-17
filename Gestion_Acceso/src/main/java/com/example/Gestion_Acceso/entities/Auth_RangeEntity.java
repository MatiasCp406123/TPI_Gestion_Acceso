package com.example.Gestion_Acceso.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "auth_ranges")
@AllArgsConstructor
@NoArgsConstructor
public class Auth_RangeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer neighbor_Id;
    @ManyToOne
    @JoinColumn(name = "user_allowed_id")
    private Ussers_AllowedEntity ussers_allowed_id;
    private LocalDate initDate;
    private LocalDate endDate;
    private Integer createdUser;
    private LocalDateTime createdDate;
    private Integer lastUpdatedUser;
    private LocalDateTime lastUpdatedDate;
}
