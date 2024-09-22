package com.example.Gestion_Acceso.repositories.Types;

import com.example.Gestion_Acceso.entities.Document_TypeEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
class Document_TypeRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private Document_TypeRepository documentTypeRepository;
    @Test
    void getByDescription() {
        Document_TypeEntity documentTypeEntity = new Document_TypeEntity();
        documentTypeEntity.setDescription("DNI");
        entityManager.persist(documentTypeEntity);
        entityManager.flush();
        Document_TypeEntity documentTypeEntity2 = documentTypeRepository.findById(documentTypeEntity.getId()).get();
        assertEquals("DNI", documentTypeEntity2.getDescription());
    }
}