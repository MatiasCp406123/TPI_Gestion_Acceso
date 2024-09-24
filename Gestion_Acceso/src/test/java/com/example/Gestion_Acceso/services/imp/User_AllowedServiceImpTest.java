package com.example.Gestion_Acceso.services.imp;

import com.example.Gestion_Acceso.dtos.Document_TypeDto;
import com.example.Gestion_Acceso.dtos.NewUserAllowedDto;
import com.example.Gestion_Acceso.dtos.User_allowedTypeDto;
import com.example.Gestion_Acceso.entities.Document_TypeEntity;
import com.example.Gestion_Acceso.entities.Users_AllowedEntity;
import com.example.Gestion_Acceso.entities.Users_allowed_typesEntity;
import com.example.Gestion_Acceso.models.UserAllowed;
import com.example.Gestion_Acceso.repositories.Types.Document_TypeRepository;
import com.example.Gestion_Acceso.repositories.Types.User_Allowed_TypeRepository;
import com.example.Gestion_Acceso.repositories.Users_AllowedRepository;
import com.example.Gestion_Acceso.services.Users_AllowedService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class User_AllowedServiceImpTest {

    @SpyBean
    private Users_AllowedService users_allowedService;
    @MockBean
    private Users_AllowedRepository users_allowedRepository;
    @MockBean
    private Document_TypeRepository document_typeRepository;
    @MockBean
    private User_Allowed_TypeRepository user_allowed_typeRepository;

    @Test
    void createUserAllowed() {
        NewUserAllowedDto newUserAllowedDto=new NewUserAllowedDto();
        newUserAllowedDto.setDocument("1234");
        newUserAllowedDto.setEmail("juan@gmail");
        newUserAllowedDto.setName("juan");
        Document_TypeDto documentTypeDto=new Document_TypeDto();
        documentTypeDto.setDescription("DNI");
        User_allowedTypeDto user_allowedTypeDto=new User_allowedTypeDto();
        user_allowedTypeDto.setDescription("Visitor");
        newUserAllowedDto.setUser_allowed_Type(user_allowedTypeDto);
        newUserAllowedDto.setDocumentType(documentTypeDto);

        Document_TypeEntity document_typeEntity=new Document_TypeEntity();
        document_typeEntity.setDescription("DNI");
        document_typeEntity.setId(1L);
        Mockito.when(document_typeRepository.getByDescription("DNI")).thenReturn(document_typeEntity);

        Users_allowed_typesEntity users_allowed_typesEntity=new Users_allowed_typesEntity();
        users_allowed_typesEntity.setId(1L);
        users_allowed_typesEntity.setDescription("Visitor");
        Mockito.when(user_allowed_typeRepository.getByDescription("Visitor")).thenReturn(users_allowed_typesEntity);

        Users_AllowedEntity users_allowedEntity=new Users_AllowedEntity();
        users_allowedEntity.setUserType(users_allowed_typesEntity.getId());
        users_allowedEntity.setName("juan");
        users_allowedEntity.setEmail("juan@gmail");
        users_allowedEntity.setDocumentType(document_typeEntity.getId());
        users_allowedEntity.setId(1L);
        users_allowedEntity.setDocument("1234");
        Mockito.when(users_allowedRepository.save(Mockito.any(Users_AllowedEntity.class))).thenReturn(users_allowedEntity);
        Mockito.when(users_allowedRepository.getByDocument("1234")).thenReturn(users_allowedEntity);

        UserAllowed userAllowed=users_allowedService.createUserAllowed(newUserAllowedDto);
        Assertions.assertNotNull(userAllowed);
        Assertions.assertEquals("juan",userAllowed.getName());
    }
}