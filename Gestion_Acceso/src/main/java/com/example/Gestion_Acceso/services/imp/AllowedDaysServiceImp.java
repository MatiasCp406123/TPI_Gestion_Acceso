package com.example.Gestion_Acceso.services.imp;

import com.example.Gestion_Acceso.dtos.Allowed_DaysDto;
import com.example.Gestion_Acceso.entities.Allowed_DaysEntity;
import com.example.Gestion_Acceso.models.AllowedDay;
import com.example.Gestion_Acceso.repositories.Allowed_daysRepository;
import com.example.Gestion_Acceso.repositories.Auth_RangesRepository;
import com.example.Gestion_Acceso.services.AllowedDaysService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class AllowedDaysServiceImp implements AllowedDaysService {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private Auth_RangesRepository authRangesRepository;
    @Autowired
    private Allowed_daysRepository allowed_daysRepository;
    @Override
    public List<AllowedDay> CreateAllowedDays(List<Allowed_DaysDto> allowedDaysDtos, Long id) {
        List<AllowedDay> allowedDayList=new ArrayList<>();
        for(Allowed_DaysDto allowedDaysDto:allowedDaysDtos){
            AllowedDay allowedDay=modelMapper.map(allowedDaysDto, AllowedDay.class);
            Allowed_DaysEntity allowed_daysEntity=modelMapper.map(allowedDay,Allowed_DaysEntity.class);
            allowed_daysEntity.setAuth_range_id(authRangesRepository.findById(id).get());
            allowed_daysEntity.setCreatedUser(1);
            allowed_daysEntity.setCreatedDate(LocalDateTime.now());
            Allowed_DaysEntity idAllowed=allowed_daysRepository.save(allowed_daysEntity);
            allowedDay.setId(idAllowed.getId());
            allowedDayList.add(allowedDay);
        }
        return allowedDayList;
    }
}
