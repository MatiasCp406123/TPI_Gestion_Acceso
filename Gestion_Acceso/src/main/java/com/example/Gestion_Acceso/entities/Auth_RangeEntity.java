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
    private Users_AllowedEntity ussers_allowed_id;
    private LocalDate init_date;
    private LocalDate end_date;
    private Integer created_user;
    private LocalDateTime created_date;
    private Integer last_updated_user;
    private LocalDateTime last_updated_date;
}
