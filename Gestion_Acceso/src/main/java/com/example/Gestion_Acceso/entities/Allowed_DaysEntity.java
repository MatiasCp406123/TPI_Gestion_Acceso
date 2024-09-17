package com.example.Gestion_Acceso.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "allowed_days")
@AllArgsConstructor
@NoArgsConstructor
public class Allowed_DaysEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private LocalDate day;
    private LocalDateTime initHour;
    private LocalDateTime endHour;

    @ManyToOne
    @JoinColumn(name = "auth_range_id")
    private Auth_RangeEntity auth_range_id;
    private Integer createdUser;
    private LocalDateTime createdDate;
    private Integer lastUpdatedUser;
    private LocalDateTime lastUpdatedDate;

}
