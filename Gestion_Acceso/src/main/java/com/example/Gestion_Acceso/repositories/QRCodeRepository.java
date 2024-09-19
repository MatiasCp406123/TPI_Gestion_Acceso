package com.example.Gestion_Acceso.repositories;

import com.example.Gestion_Acceso.entities.QRCode_Entity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface QRCodeRepository extends JpaRepository<QRCode_Entity,Long> {
    Optional<QRCode_Entity> findByQrCodeData(String qrCodeData);
    Optional<QRCode_Entity> findByUserAllowedId(Long userAllowedId);
}
