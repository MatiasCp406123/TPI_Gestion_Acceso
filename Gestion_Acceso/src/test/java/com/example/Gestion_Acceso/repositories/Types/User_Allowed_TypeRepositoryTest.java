package com.example.Gestion_Acceso.repositories.Types;

import com.example.Gestion_Acceso.entities.Users_allowed_typesEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
class User_Allowed_TypeRepositoryTest {

    @Autowired
    private TestEntityManager testEntityManager;
    @Autowired
    private User_Allowed_TypeRepository user_allowed_typeRepository;
    @Test
    void getByDescription() {
        Users_allowed_typesEntity users_allowed_typesEntity=new Users_allowed_typesEntity();
        users_allowed_typesEntity.setDescription("Worker");
        testEntityManager.persist(users_allowed_typesEntity);
        testEntityManager.flush();
        Users_allowed_typesEntity response=user_allowed_typeRepository.getByDescription("Worker");
        Assertions.assertNotNull(response);
        Assertions.assertEquals("Worker",response.getDescription());

    }
}