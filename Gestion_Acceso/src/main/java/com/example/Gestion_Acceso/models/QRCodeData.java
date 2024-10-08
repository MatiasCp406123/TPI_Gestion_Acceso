package com.example.Gestion_Acceso.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class QRCodeData {
    private Integer neighborId;
    private String name;
    private String document;
    private String documentType;
    private String vehicle;
    private String plate;
    private LocalDateTime generatedDate;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

}
