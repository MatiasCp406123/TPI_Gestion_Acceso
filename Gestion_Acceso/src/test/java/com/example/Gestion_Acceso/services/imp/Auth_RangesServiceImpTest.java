package com.example.Gestion_Acceso.services.imp;

import com.example.Gestion_Acceso.dtos.NewAuthRangeDto;
import com.example.Gestion_Acceso.entities.Auth_RangeEntity;
import com.example.Gestion_Acceso.entities.Users_AllowedEntity;
import com.example.Gestion_Acceso.models.AuthRange;
import com.example.Gestion_Acceso.repositories.Auth_RangesRepository;
import com.example.Gestion_Acceso.repositories.Users_AllowedRepository;
import com.example.Gestion_Acceso.services.Auth_RangesService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;

import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class Auth_RangesServiceImpTest {
    @SpyBean
    private Auth_RangesService authRangesService;
    @MockBean
    private Auth_RangesRepository auth_rangesRepository;
    @MockBean
    private Users_AllowedRepository users_allowedRepository;

    @Test
    void creatRange() {
        NewAuthRangeDto newAuthRangeDto=new NewAuthRangeDto();
        newAuthRangeDto.setInit_date(LocalDate.now());
        newAuthRangeDto.setNeighbor_id(1);
        newAuthRangeDto.setEnd_date(LocalDate.now());

        Users_AllowedEntity users_allowedEntity=new Users_AllowedEntity();
        users_allowedEntity.setId(1L);
        users_allowedEntity.setDocument("1234");
        Mockito.when(users_allowedRepository.findById(1L)).thenReturn(Optional.of(users_allowedEntity));

        Auth_RangeEntity auth_rangeEntity=new Auth_RangeEntity();
        auth_rangeEntity.setId(1L);
        auth_rangeEntity.setInit_date(LocalDate.now());
        auth_rangeEntity.setEnd_date(LocalDate.now());
        auth_rangeEntity.setNeighbor_Id(1);
        auth_rangeEntity.setUssers_allowed_id(users_allowedEntity);
        Mockito.when(auth_rangesRepository.save(Mockito.any(Auth_RangeEntity.class))).thenReturn(auth_rangeEntity);

        AuthRange authRange=authRangesService.creatRange(newAuthRangeDto,1L);
        Assertions.assertNotNull(authRange);
        Assertions.assertEquals(auth_rangeEntity.getNeighbor_Id(),authRange.getNeighbor_Id());

    }
}