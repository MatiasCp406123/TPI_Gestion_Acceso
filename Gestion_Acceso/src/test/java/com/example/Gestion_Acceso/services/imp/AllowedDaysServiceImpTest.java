package com.example.Gestion_Acceso.services.imp;

import com.example.Gestion_Acceso.dtos.Allowed_DaysDto;
import com.example.Gestion_Acceso.entities.Allowed_DaysEntity;
import com.example.Gestion_Acceso.entities.Auth_RangeEntity;
import com.example.Gestion_Acceso.models.AllowedDay;
import com.example.Gestion_Acceso.repositories.Allowed_daysRepository;
import com.example.Gestion_Acceso.repositories.Auth_RangesRepository;
import com.example.Gestion_Acceso.services.AllowedDaysService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class AllowedDaysServiceImpTest {

    @SpyBean
    private AllowedDaysService allowedDaysService;
    @MockBean
    private Allowed_daysRepository allowed_daysRepository;
    @MockBean
    private Auth_RangesRepository authRangesRepository;
    @Test
    void createAllowedDays() {
        Auth_RangeEntity auth_rangeEntity=new Auth_RangeEntity();
        auth_rangeEntity.setId(1L);
        Mockito.when(authRangesRepository.findById(1L)).thenReturn(Optional.of(auth_rangeEntity));

        Allowed_DaysEntity allowed_daysEntity=new Allowed_DaysEntity();
        allowed_daysEntity.setCreatedDate(LocalDateTime.now());
        allowed_daysEntity.setId(1L);
        allowed_daysEntity.setCreatedUser(1);
        allowed_daysEntity.setInitHour(LocalDateTime.now());
        allowed_daysEntity.setDay(LocalDate.now());
        allowed_daysEntity.setEndHour(LocalDateTime.now());
        allowed_daysEntity.setAuth_range_id(auth_rangeEntity);
        Mockito.when(allowed_daysRepository.save(Mockito.any(Allowed_DaysEntity.class))).thenReturn(allowed_daysEntity);

        Allowed_DaysDto allowedDaysDto=new Allowed_DaysDto();
        allowedDaysDto.setInit_date(LocalDate.now());
        allowedDaysDto.setDay(LocalDate.now());
        allowedDaysDto.setEnd_date(LocalDate.now());
        List<Allowed_DaysDto>allowedDaysDtos=new ArrayList<>();
        allowedDaysDtos.add(allowedDaysDto);

        List<AllowedDay>response=allowedDaysService.CreateAllowedDays(allowedDaysDtos,1L);
        Assertions.assertNotNull(response);
        Assertions.assertEquals(allowed_daysEntity.getDay(),response.get(0).getDay());

    }
}