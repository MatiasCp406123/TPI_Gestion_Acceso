package com.example.Gestion_Acceso.services.imp;

import com.example.Gestion_Acceso.dtos.NewUserAllowedDto;
import com.example.Gestion_Acceso.entities.Document_TypeEntity;
import com.example.Gestion_Acceso.entities.Users_AllowedEntity;
import com.example.Gestion_Acceso.entities.Users_allowed_typesEntity;
import com.example.Gestion_Acceso.models.DocumentType;
import com.example.Gestion_Acceso.models.UserAllowed;
import com.example.Gestion_Acceso.models.UserAllowedType;
import com.example.Gestion_Acceso.repositories.Types.Document_TypeRepository;
import com.example.Gestion_Acceso.repositories.Types.User_Allowed_TypeRepository;
import com.example.Gestion_Acceso.repositories.Users_AllowedRepository;
import com.example.Gestion_Acceso.services.Users_AllowedService;
import jakarta.annotation.PostConstruct;
import org.apache.catalina.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class User_AllowedServiceImp implements Users_AllowedService {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private Users_AllowedRepository users_allowedRepository;
    @Autowired
    private Document_TypeRepository document_typeRepository;
    @Autowired
    private User_Allowed_TypeRepository user_allowed_typeRepository;
    @Override
    public UserAllowed createUserAllowed(NewUserAllowedDto newUserAllowedDto) {
        UserAllowedType userAllowedType=modelMapper.map(user_allowed_typeRepository.getByDescription(newUserAllowedDto.getUser_allowed_Type().getDescription()), UserAllowedType.class);
        DocumentType documentType=modelMapper.map(document_typeRepository.getByDescription(newUserAllowedDto.getDocumentType().getDescription()), DocumentType.class);
        UserAllowed userAllowed=new UserAllowed();
        userAllowed.setDocument(newUserAllowedDto.getDocument());
        userAllowed.setEmail(newUserAllowedDto.getEmail());
        userAllowed.setUserType(userAllowedType.getId());
        userAllowed.setDocumentType(documentType.getId());
        userAllowed.setName(newUserAllowedDto.getName());
        Users_AllowedEntity usersAllowedEntity=modelMapper.map(userAllowed,Users_AllowedEntity.class);
        usersAllowedEntity.setDocumentType(documentType.getId());
        usersAllowedEntity.setUserType(userAllowedType.getId());
        usersAllowedEntity.setCreated_user(1);
        usersAllowedEntity.setCreated_date(LocalDateTime.now());
        UserAllowed users_allowed=modelMapper.map(users_allowedRepository.save(usersAllowedEntity), UserAllowed.class);
        userAllowed.setId(users_allowedRepository.getByDocument(newUserAllowedDto.getDocument()).getId());
        return users_allowed;
    }
}
