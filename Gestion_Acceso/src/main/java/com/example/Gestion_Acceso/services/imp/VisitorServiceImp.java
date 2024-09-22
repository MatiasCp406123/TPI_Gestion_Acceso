package com.example.Gestion_Acceso.services.imp;

import com.example.Gestion_Acceso.dtos.NewVisitorDto;
import com.example.Gestion_Acceso.models.*;
import com.example.Gestion_Acceso.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VisitorServiceImp implements VisitorService {
    @Autowired
    private Users_AllowedService users_allowedService;
    @Autowired
    private Auth_RangesService authRangesService;
    @Autowired
    private AllowedDaysService allowedDaysService;
    @Autowired
    private VehicleService vehicleService;
    @Override
    public Visitors crateVisitor(NewVisitorDto newVisitorDto) {
        Visitors visitors=new Visitors();
        UserAllowed userAllowed=users_allowedService.createUserAllowed(newVisitorDto.getNewUserAllowedDto());
        AuthRange authRange=authRangesService.creatRange(newVisitorDto.getNewAuthRangeDto(),userAllowed.getId());
        List<AllowedDay>allowedDayList=allowedDaysService.CreateAllowedDays(newVisitorDto.getNewAuthRangeDto().getAllowedDaysDtos(),authRange.getId());
        Vehicle vehicle=vehicleService.createVehicle(newVisitorDto.getNewVehicleDto());
        if(vehicle==null){
            List<Vehicle>vehicleList=new ArrayList<>();
            vehicleList.add(vehicle);
            userAllowed.setVehicles(vehicleList);
        }
        authRange.setAllowedDays(allowedDayList);
        List<AuthRange>authRangeList=new ArrayList<>();
        authRangeList.add(authRange);
        userAllowed.setAuthRanges(authRangeList);
        visitors.setUserAllowed(userAllowed);
        return  visitors;
    }
}
