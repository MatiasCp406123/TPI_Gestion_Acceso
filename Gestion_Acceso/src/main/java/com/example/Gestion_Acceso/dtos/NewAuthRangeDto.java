package com.example.Gestion_Acceso.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class NewAuthRangeDto {
    private int neighbor_id;
    private Date init_date;
    private Date end_date;
    private List<Allowed_DaysDto>allowedDaysDtos;
}
