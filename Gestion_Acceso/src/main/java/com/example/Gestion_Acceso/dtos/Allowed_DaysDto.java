package com.example.Gestion_Acceso.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Allowed_DaysDto {
    private LocalDate day;
    private LocalDate init_date;
    private LocalDate end_date;
}
