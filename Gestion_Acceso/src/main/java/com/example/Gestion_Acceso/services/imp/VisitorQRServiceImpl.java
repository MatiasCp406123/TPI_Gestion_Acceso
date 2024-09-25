package com.example.Gestion_Acceso.services.imp;

import com.example.Gestion_Acceso.entities.QRCode_Entity;
import com.example.Gestion_Acceso.entities.Users_AllowedEntity;
import com.example.Gestion_Acceso.models.QRCodeData;

import com.example.Gestion_Acceso.models.UserAllowed;
import com.example.Gestion_Acceso.repositories.QRCodeRepository;
import com.example.Gestion_Acceso.repositories.Types.Document_TypeRepository;
import com.example.Gestion_Acceso.repositories.Types.Vehicle_TypeRepository;
import com.example.Gestion_Acceso.services.VisitorQRService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.catalina.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class VisitorQRServiceImpl implements VisitorQRService {
    @Autowired
    private Document_TypeRepository document_typeRepository;

    @Autowired
    private Vehicle_TypeRepository vehicle_typeRepository;

    @Autowired
    private QRCodeRepository qrCodeRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public QRCode_Entity generateAndSaveQRForVisitor(List<UserAllowed> visitors) throws Exception {
        List<QRCodeData>qrCodeEntityList=new ArrayList<>();
        QRCodeData qrCodeData = new QRCodeData();
        for(UserAllowed visitor:visitors) {
            if (visitor.getVehicles() != null) {

                qrCodeData.setNeighborId(visitor.getAuthRanges().get(0).getNeighbor_Id());
                qrCodeData.setName(visitor.getName());
                qrCodeData.setDocument(visitor.getDocument());
                qrCodeData.setDocumentType(document_typeRepository.findById(visitor.getDocumentType()).get().getDescription());
                qrCodeData.setVehicle(vehicle_typeRepository.findById(visitor.getVehicles().get(0).getVehicleType()).get().getDescription());
                qrCodeData.setPlate(visitor.getVehicles().get(0).getPlate());
                qrCodeData.setGeneratedDate(LocalDateTime.now());
                qrCodeData.setStartDate(visitor.getAuthRanges().get(0).getInitDate().atStartOfDay());
                qrCodeData.setEndDate(visitor.getAuthRanges().get(0).getEndDate().atTime(23, 59, 59));


            } else {
                qrCodeData.setNeighborId(visitor.getAuthRanges().get(0).getNeighbor_Id());
                qrCodeData.setName(visitor.getName());
                qrCodeData.setDocument(visitor.getDocument());
                qrCodeData.setDocumentType(document_typeRepository.findById(visitor.getDocumentType()).get().getDescription());
                qrCodeData.setVehicle("not vehicle");
                qrCodeData.setPlate("not plate");
                qrCodeData.setGeneratedDate(LocalDateTime.now());
                qrCodeData.setStartDate(visitor.getAuthRanges().get(0).getInitDate().atStartOfDay());
                qrCodeData.setEndDate(visitor.getAuthRanges().get(0).getEndDate().atTime(23, 59, 59));

            }
            qrCodeEntityList.add(qrCodeData);
        }

            String qrCodeJson = objectMapper.writeValueAsString(qrCodeEntityList);

            QRCode_Entity qrCodeEntity = new QRCode_Entity();
            qrCodeEntity.setQrCodeData(qrCodeJson);
            qrCodeEntity.setCreatedDate(LocalDateTime.now());
            qrCodeEntity.setIsValid(true);
            for(UserAllowed visitor:visitors) {
                Users_AllowedEntity userAllowedEntity = modelMapper.map(visitor, Users_AllowedEntity.class);
                qrCodeEntity.setUserAllowed(userAllowedEntity);
            }
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
