package com.example.Gestion_Acceso.services.imp;

import com.example.Gestion_Acceso.dtos.NewUserAllowedDto;
import com.example.Gestion_Acceso.entities.*;
import com.example.Gestion_Acceso.models.*;
import com.example.Gestion_Acceso.repositories.QRCodeRepository;
import com.example.Gestion_Acceso.repositories.Types.Document_TypeRepository;
import com.example.Gestion_Acceso.repositories.Types.Vehicle_TypeRepository;
import com.example.Gestion_Acceso.services.VisitorQRService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.SneakyThrows;
import org.apache.catalina.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
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
    @SpyBean
    private  ObjectMapper objectMapper;
    @MockBean
    private Document_TypeRepository document_typeRepository;
    @MockBean
    private Vehicle_TypeRepository vehicle_typeRepository;
    @Test
    void generateAndSaveQRForVisitor() throws Exception {

        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

        Mockito.verify(objectMapper).configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

        UserAllowed userAllowedOne=new UserAllowed();
        userAllowedOne.setName("Fede");
        userAllowedOne.setId(1L);
        userAllowedOne.setEmail("fede@gmail.com");
        userAllowedOne.setDocument("40662469");
        List<UserAllowed>userAlloweds=new ArrayList<>();
        userAlloweds.add(userAllowedOne);

        AuthRange authRangeUno = new AuthRange();
        authRangeUno.setNeighbor_Id(9);
        authRangeUno.setInitDate(LocalDate.now());
        authRangeUno.setEndDate(LocalDate.now().plusDays(7));
        List<AuthRange> ranges=new ArrayList<>();
        ranges.add(authRangeUno);

        Vehicle vehicleOne = new Vehicle();
        vehicleOne.setVehicleType(1L);
        vehicleOne.setId(1L);
        List<Vehicle> vehicles=new ArrayList<>();
        vehicles.add(vehicleOne);

        DocumentType documentType=new DocumentType();
        documentType.setId(1L);
        documentType.setDescription("DNI");

        userAllowedOne.setDocumentType(documentType.getId());
        userAllowedOne.setVehicles(vehicles);
        userAllowedOne.setAuthRanges(ranges);

        Document_TypeEntity documentTypeEntity=new Document_TypeEntity();
        documentTypeEntity.setId(1L);
        documentTypeEntity.setDescription("DNI");

        Vehicles_TypesEntity vehiclesTypesEntity=new Vehicles_TypesEntity();
        vehiclesTypesEntity.setId(1L);
        Mockito.when(document_typeRepository.findById(1L)).thenReturn(Optional.of(documentTypeEntity));
        Mockito.when(vehicle_typeRepository.findById(1L)).thenReturn(Optional.of(vehiclesTypesEntity));


        QRCode_Entity savedQRCodeEntity = new QRCode_Entity();
        savedQRCodeEntity.setId(1L);
        savedQRCodeEntity.setQrCodeData("mocked QR code data");
        savedQRCodeEntity.setCreatedDate(LocalDateTime.now());
        savedQRCodeEntity.setIsValid(true);
        when(qrCodeRepository.save(any(QRCode_Entity.class))).thenReturn(savedQRCodeEntity);

        QRCode_Entity result = visitorQRService.generateAndSaveQRForVisitor(userAlloweds);


        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertTrue(result.getIsValid());
        assertNotNull(result.getQrCodeData());
        verify(document_typeRepository).findById(1L);
        verify(vehicle_typeRepository).findById(1L);
        verify(qrCodeRepository).save(any(QRCode_Entity.class));
        assertFalse(objectMapper.getSerializationConfig().isEnabled(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS));

      }





    @Test
    void validateQRCode() {
    }

    @Test
    void getQRCodeById() {
//        Long QrCodeId=22L;
//
//        Users_allowed_typesEntity usersAllowedTypesEntity=new Users_allowed_typesEntity();
//        usersAllowedTypesEntity.setId(1L);
//        usersAllowedTypesEntity.setDescription("Fede");
//        usersAllowedTypesEntity.setCreated_user(33);
//
//        Document_TypeEntity documentTypeEntity=new Document_TypeEntity();
//        documentTypeEntity.setId(1l);
//        documentTypeEntity.setDescription("DNI");
//
//        Users_AllowedEntity users_allowedEntity=new Users_AllowedEntity();
//        users_allowedEntity.setId(1L);
//        users_allowedEntity.setUserType(usersAllowedTypesEntity);
//        users_allowedEntity.setDocumentType(documentTypeEntity);
//
//
//        QRCode_Entity qrCodeEntity= new QRCode_Entity();
//        qrCodeEntity.setId(QrCodeId);
//        qrCodeEntity.setQrCodeData("asd");
//        qrCodeEntity.setIsValid(true);
//        qrCodeEntity.setUserAllowed(users_allowedEntity);
//
//        Mockito.when(qrCodeRepository.findById(22L)).thenReturn(Optional.of(qrCodeEntity));
//        QRCode_Entity result=qrCodeRepository.findById(22l).get();
//
//        Assertions.assertNotNull(result);
//        Assertions.assertEquals(qrCodeEntity.getId(),result.getId());        Long QrCodeId=22L;
//
//        Users_allowed_typesEntity usersAllowedTypesEntity=new Users_allowed_typesEntity();
//        usersAllowedTypesEntity.setId(1L);
//        usersAllowedTypesEntity.setDescription("Fede");
//        usersAllowedTypesEntity.setCreated_user(33);
//
//        Document_TypeEntity documentTypeEntity=new Document_TypeEntity();
//        documentTypeEntity.setId(1l);
//        documentTypeEntity.setDescription("DNI");
//
//        Users_AllowedEntity users_allowedEntity=new Users_AllowedEntity();
//        users_allowedEntity.setId(1L);
//        users_allowedEntity.setUserType(usersAllowedTypesEntity);
//        users_allowedEntity.setDocumentType(documentTypeEntity);
//
//
//        QRCode_Entity qrCodeEntity= new QRCode_Entity();
//        qrCodeEntity.setId(QrCodeId);
//        qrCodeEntity.setQrCodeData("asd");
//        qrCodeEntity.setIsValid(true);
//        qrCodeEntity.setUserAllowed(users_allowedEntity);
//
//        Mockito.when(qrCodeRepository.findById(22L)).thenReturn(Optional.of(qrCodeEntity));
//        QRCode_Entity result=qrCodeRepository.findById(22l).get();
//
//        Assertions.assertNotNull(result);
//        Assertions.assertEquals(qrCodeEntity.getId(),result.getId());
    }
}