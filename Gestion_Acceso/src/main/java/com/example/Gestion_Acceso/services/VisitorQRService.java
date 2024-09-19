package com.example.Gestion_Acceso.services;
import com.example.Gestion_Acceso.entities.QRCode_Entity;
import com.example.Gestion_Acceso.models.Visitors;
import com.google.zxing.qrcode.encoder.QRCode;
import org.springframework.stereotype.Service;

@Service
public interface VisitorQRService {
    QRCode_Entity generateAndSaveQRForVisitor(Visitors visitor) throws Exception;
    boolean validateQRCode(String qrCodeData) throws Exception;
    QRCode_Entity getQRCodeById(Long id) throws Exception;

}
