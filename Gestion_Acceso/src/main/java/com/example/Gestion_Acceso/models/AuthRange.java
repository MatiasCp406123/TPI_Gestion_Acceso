package com.example.Gestion_Acceso.models;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthRange {
    private Long id;
    private Integer neighbor_Id;
    private LocalDate initDate;
    private LocalDate endDate;
    private List<AllowedDay> allowedDays;

}
