package com.example.Gestion_Acceso.services.imp;

import com.example.Gestion_Acceso.dtos.NewAuthRangeDto;
import com.example.Gestion_Acceso.entities.Auth_RangeEntity;
import com.example.Gestion_Acceso.models.AuthRange;
import com.example.Gestion_Acceso.repositories.Auth_RangesRepository;
import com.example.Gestion_Acceso.repositories.Users_AllowedRepository;
import com.example.Gestion_Acceso.services.Auth_RangesService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class Auth_RangesServiceImp implements Auth_RangesService {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private Auth_RangesRepository authRangesRepository;
    @Autowired
    private Users_AllowedRepository users_allowedRepository;
    @Override
    public AuthRange creatRange(NewAuthRangeDto newAuthRangeDto, Long id) {
       AuthRange authRange=modelMapper.map(newAuthRangeDto, AuthRange.class);
        Auth_RangeEntity auth_rangeEntity=modelMapper.map(authRange, Auth_RangeEntity.class);
        auth_rangeEntity.setUssers_allowed_id(users_allowedRepository.findById(id).get());
        auth_rangeEntity.setCreated_user(1);
        auth_rangeEntity.setCreated_date(LocalDateTime.now());
        Auth_RangeEntity idAuth=authRangesRepository.save(auth_rangeEntity);
        authRange.setId(idAuth.getId());
        return authRange;
    }
}
