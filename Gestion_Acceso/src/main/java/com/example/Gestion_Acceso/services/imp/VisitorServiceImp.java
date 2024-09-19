package com.example.Gestion_Acceso.services.imp;

import com.example.Gestion_Acceso.dtos.NewVisitorDto;
import com.example.Gestion_Acceso.models.AllowedDay;
import com.example.Gestion_Acceso.models.AuthRange;
import com.example.Gestion_Acceso.models.UserAllowed;
import com.example.Gestion_Acceso.models.Visitors;
import com.example.Gestion_Acceso.services.AllowedDaysService;
import com.example.Gestion_Acceso.services.Auth_RangesService;
import com.example.Gestion_Acceso.services.Users_AllowedService;
import com.example.Gestion_Acceso.services.VisitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VisitorServiceImp implements VisitorService {
    @Autowired
    private Users_AllowedService users_allowedService;
    @Autowired
    private Auth_RangesService authRangesService;
    @Autowired
    private AllowedDaysService allowedDaysService;
    @Override
    public Visitors crateVisitor(NewVisitorDto newVisitorDto) {
        Visitors visitors=new Visitors();
        UserAllowed userAllowed=users_allowedService.createUserAllowed(newVisitorDto.getNewUserAllowedDto());
        AuthRange authRange=authRangesService.creatRange(newVisitorDto.getNewAuthRangeDto(),userAllowed.getId());
        List<AllowedDay>allowedDayList=allowedDaysService.CreateAllowedDays(newVisitorDto.getNewAuthRangeDto().getAllowedDaysDtos(),authRange.getId());
        authRange.setAllowedDays(allowedDayList);
        visitors.setAuthRange(authRange);
        visitors.setUserAllowed(userAllowed);
        return  visitors;
    }
}
