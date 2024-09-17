package com.example.Gestion_Acceso.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@Table(name = "qr_codes")
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class QRCode_Entity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "qr_code_data", columnDefinition = "TEXT")
    private String qrCodeData;

    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @Column(name = "is_valid")
    private Boolean isValid = true;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_allowed_id", nullable = false)
    private Users_AllowedEntity userAllowed;

}
