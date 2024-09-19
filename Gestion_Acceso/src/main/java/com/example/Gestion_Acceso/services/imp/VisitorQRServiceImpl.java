package com.example.Gestion_Acceso.services.imp;

import com.example.Gestion_Acceso.entities.QRCode_Entity;
import com.example.Gestion_Acceso.entities.Users_AllowedEntity;
import com.example.Gestion_Acceso.models.QRCodeData;
import com.example.Gestion_Acceso.models.Visitors;

import com.example.Gestion_Acceso.repositories.QRCodeRepository;
import com.example.Gestion_Acceso.services.QrCodeService;
import com.example.Gestion_Acceso.services.VisitorQRService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.zxing.qrcode.encoder.QRCode;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class VisitorQRServiceImpl implements VisitorQRService {

    @Autowired
    private QrCodeService qrCodeService;

    @Autowired
    private QRCodeRepository qrCodeRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public QRCode_Entity generateAndSaveQRForVisitor(Visitors visitor) throws Exception {
        QRCodeData qrCodeData = new QRCodeData(
                UUID.randomUUID().toString(),
                visitor.getUserAllowed().getDocument(),
                visitor.getUserAllowed().getDocumentType().getDescription(),
                LocalDateTime.now(),
                visitor.getAuthRange().getInitDate().atStartOfDay(),
                visitor.getAuthRange().getEndDate().atTime(23, 59, 59)
        );

        String qrCodeJson = objectMapper.writeValueAsString(qrCodeData);

        QRCode_Entity qrCodeEntity = new QRCode_Entity();
        qrCodeEntity.setQrCodeData(qrCodeJson);
        qrCodeEntity.setCreatedDate(LocalDateTime.now());
        qrCodeEntity.setIsValid(true);
        Users_AllowedEntity userAllowedEntity = modelMapper.map(visitor.getUserAllowed(), Users_AllowedEntity.class);
        qrCodeEntity.setUserAllowed(userAllowedEntity);
        return qrCodeRepository.save(qrCodeEntity);
    }

    @Override
    public boolean validateQRCode(String qrCodeData) throws Exception {
        Optional<QRCode_Entity> qrCodeEntityOpt = qrCodeRepository.findByQrCodeData(qrCodeData);

        if (qrCodeEntityOpt.isEmpty()) {
            return false;
        }

        QRCode_Entity qrCodeEntity = qrCodeEntityOpt.get();

        if (!qrCodeEntity.getIsValid()) {
            return false;
        }

        QRCodeData data = objectMapper.readValue(qrCodeEntity.getQrCodeData(), QRCodeData.class);
        LocalDateTime now = LocalDateTime.now();

        return now.isAfter(data.getStartDate()) && now.isBefore(data.getEndDate());
    }

    @Override
    public QRCode_Entity getQRCodeById(Long id) throws Exception {
        return qrCodeRepository.findById(id)
                .orElseThrow(() -> new Exception("QR Code not found"));
    }
}
