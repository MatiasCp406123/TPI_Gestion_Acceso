package com.example.Gestion_Acceso.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class NewAuthRangeDto {
    private int neighbor_id;
    private LocalDate init_date;
    private LocalDate end_date;
    private List<Allowed_DaysDto>allowedDaysDtos;
}
