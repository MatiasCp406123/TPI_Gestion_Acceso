package com.example.Gestion_Acceso.repositories;

import com.example.Gestion_Acceso.entities.QRCode_Entity;
import com.example.Gestion_Acceso.entities.Users_AllowedEntity;
import org.apache.catalina.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
class QRCodeRepositoryTest {

    @Autowired
    private TestEntityManager testEntityManager;
    @Autowired
    private QRCodeRepository qrCodeRepository;
    @Test
    void findByQrCodeData() {
        Users_AllowedEntity users_allowedEntity=new Users_AllowedEntity();
        users_allowedEntity.setEmail("eeefe@");
        testEntityManager.persist(users_allowedEntity);
        testEntityManager.flush();
        QRCode_Entity qrCodeEntity=new QRCode_Entity();
        qrCodeEntity.setQrCodeData("Visitor:1");
        qrCodeEntity.setUserAllowed(users_allowedEntity);
        testEntityManager.persist(qrCodeEntity);
        testEntityManager.flush();
        Optional<QRCode_Entity> qrCode=qrCodeRepository.findByQrCodeData(qrCodeEntity.getQrCodeData());
        Assertions.assertNotNull(qrCode);
        Assertions.assertEquals(qrCodeEntity.getQrCodeData(),qrCode.get().getQrCodeData());
    }
}