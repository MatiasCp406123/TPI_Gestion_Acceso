package com.example.Gestion_Acceso.services.imp;

import com.example.Gestion_Acceso.entities.Document_TypeEntity;
import com.example.Gestion_Acceso.entities.QRCode_Entity;
import com.example.Gestion_Acceso.entities.Users_AllowedEntity;
import com.example.Gestion_Acceso.entities.Users_allowed_typesEntity;
import com.example.Gestion_Acceso.models.*;
import com.example.Gestion_Acceso.repositories.QRCodeRepository;
import com.example.Gestion_Acceso.services.VisitorQRService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class VisitorQRServiceImplTest {
    @SpyBean
    private VisitorQRService visitorQRService;
    @MockBean
    private QRCodeRepository qrCodeRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private ObjectMapper objectMapper;

    @SneakyThrows // por la linea 86 por que object mapper (el metodo writeValueAsString) puede lanzar una exepcion no controlada JsonProcessingException
    @Test
    void generateAndSaveQRForVisitor()  {
        Visitors visitors=new Visitors();
        UserAllowed userAllowed=new UserAllowed();

        userAllowed.setName("Fede");
        userAllowed.setDocument("1111");

        List<AuthRange> authRanges=new ArrayList<>();
        AuthRange ar=new AuthRange();
        ar.setNeighbor_Id(7);
        ar.setId(1l);
        LocalDate fecha1=LocalDate.now();
        LocalDate fecha2=LocalDate.of(2024,9,24);
        ar.setInitDate(fecha1);
        ar.setEndDate(fecha2);
        authRanges.add(ar);
        userAllowed.setAuthRanges(authRanges);


        VehicleType vehicleType =new VehicleType();
        vehicleType.setId(1l);
        vehicleType.setDescription("dni");
        List<Vehicle> vehicleList=new ArrayList<>();
        Vehicle v=new Vehicle();
        v.setId(1l);
        v.setPlate("ag-435-f");
        v.setVehicleTypeId(vehicleType);
        vehicleList.add(v);
        userAllowed.setVehicles(vehicleList);

        DocumentType documentType=new DocumentType();
        documentType.setDescription("Dni");
        userAllowed.setDocumentType(documentType);

        visitors.setUserAllowed(userAllowed);

        QRCodeData qrCodeData = new QRCodeData();
        qrCodeData.setNeighborId(visitors.getUserAllowed().getAuthRanges().get(0).getNeighbor_Id());
        qrCodeData.setName(visitors.getUserAllowed().getName());
        qrCodeData.setDocument(visitors.getUserAllowed().getDocument());
        qrCodeData.setDocumentType(visitors.getUserAllowed().getDocumentType().getDescription());
        qrCodeData.setVehicle(visitors.getUserAllowed().getVehicles().get(0).getVehicleTypeId().getDescription());
        qrCodeData.setPlate(visitors.getUserAllowed().getVehicles().get(0).getPlate());
        qrCodeData.setGeneratedDate(LocalDateTime.now());
        qrCodeData.setStartDate(visitors.getUserAllowed().getAuthRanges().get(0).getInitDate().atStartOfDay());
        qrCodeData.setEndDate(visitors.getUserAllowed().getAuthRanges().get(0).getEndDate().atTime(23, 59, 59));

        QRCode_Entity qrCodeEntity=new QRCode_Entity();
        qrCodeEntity.setQrCodeData(objectMapper.writeValueAsString(qrCodeData));
        qrCodeEntity.setCreatedDate(LocalDateTime.now());
        qrCodeEntity.setIsValid(true);
        qrCodeEntity.setUserAllowed(modelMapper.map(visitors.getUserAllowed(), Users_AllowedEntity.class));
        when(qrCodeRepository.save(any(QRCode_Entity.class))).thenReturn(qrCodeEntity);

        QRCode_Entity result = visitorQRService.generateAndSaveQRForVisitor(visitors);
        assertNotNull(result);
        assertEquals(qrCodeEntity.getQrCodeData(), result.getQrCodeData());
        assertEquals(qrCodeEntity.getIsValid(), result.getIsValid());

        verify(qrCodeRepository).save(any(QRCode_Entity.class));
    }

    @Test
    void validateQRCode() {
    }

    @Test
    void getQRCodeById() {
        Long QrCodeId=22L;

        Users_allowed_typesEntity usersAllowedTypesEntity=new Users_allowed_typesEntity();
        usersAllowedTypesEntity.setId(1L);
        usersAllowedTypesEntity.setDescription("Fede");
        usersAllowedTypesEntity.setCreated_user(33);

        Document_TypeEntity documentTypeEntity=new Document_TypeEntity();
        documentTypeEntity.setId(1l);
        documentTypeEntity.setDescription("DNI");

        Users_AllowedEntity users_allowedEntity=new Users_AllowedEntity();
        users_allowedEntity.setId(1L);
        users_allowedEntity.setUserType(usersAllowedTypesEntity);
        users_allowedEntity.setDocumentType(documentTypeEntity);


        QRCode_Entity qrCodeEntity= new QRCode_Entity();
        qrCodeEntity.setId(QrCodeId);
        qrCodeEntity.setQrCodeData("asd");
        qrCodeEntity.setIsValid(true);
        qrCodeEntity.setUserAllowed(users_allowedEntity);

        Mockito.when(qrCodeRepository.findById(22L)).thenReturn(Optional.of(qrCodeEntity));
        QRCode_Entity result=qrCodeRepository.findById(22l).get();

        Assertions.assertNotNull(result);
        Assertions.assertEquals(qrCodeEntity.getId(),result.getId());



    }
}