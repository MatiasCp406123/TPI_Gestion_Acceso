package com.example.Gestion_Acceso.services;
import com.example.Gestion_Acceso.entities.QRCode_Entity;
import com.example.Gestion_Acceso.models.UserAllowed;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface VisitorQRService {
    QRCode_Entity generateAndSaveQRForVisitor(List<UserAllowed> visitor) throws Exception;
    boolean validateQRCode(String qrCodeData) throws Exception;
    QRCode_Entity getQRCodeById(Long id) throws Exception;

}
