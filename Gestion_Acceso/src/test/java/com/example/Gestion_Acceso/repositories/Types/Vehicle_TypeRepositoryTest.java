package com.example.Gestion_Acceso.repositories.Types;

import com.example.Gestion_Acceso.entities.Vehicles_TypesEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
class Vehicle_TypeRepositoryTest {

    @Autowired
    private TestEntityManager testEntityManager;
    @Autowired
    private Vehicle_TypeRepository vehicle_typeRepository;
    @Test
    void getByDescription() {
        Vehicles_TypesEntity vehiclesTypesEntity=new Vehicles_TypesEntity();
        vehiclesTypesEntity.setDescription("Truck");
        testEntityManager.persist(vehiclesTypesEntity);
        testEntityManager.flush();
        Vehicles_TypesEntity response=vehicle_typeRepository.getByDescription("Truck");
        Assertions.assertNotNull(response);
        Assertions.assertEquals("Truck",response.getDescription());
    }
}