package com.example.Gestion_Acceso.repositories;

import com.example.Gestion_Acceso.entities.Users_AllowedEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
class Users_AllowedRepositoryTest {

    @Autowired
    private TestEntityManager testEntityManager;
    @Autowired
    private Users_AllowedRepository users_allowedRepository;
    @Test
    void getByDocument() {
        Users_AllowedEntity users_allowedEntity=new Users_AllowedEntity();
        users_allowedEntity.setDocument("1213");
        users_allowedEntity.setEmail("eeefe@");
        testEntityManager.persist(users_allowedEntity);
        testEntityManager.flush();
        Users_AllowedEntity response=users_allowedRepository.getByDocument(users_allowedEntity.getDocument());
        Assertions.assertNotNull(response);
        Assertions.assertEquals(users_allowedEntity.getDocument(),response.getDocument());
    }
}