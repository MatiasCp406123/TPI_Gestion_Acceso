package com.example.Gestion_Acceso.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AllowedDay {
    private Long id;
    private LocalDate day;
    private LocalDate init_date;
    private LocalDate end_date;
}
