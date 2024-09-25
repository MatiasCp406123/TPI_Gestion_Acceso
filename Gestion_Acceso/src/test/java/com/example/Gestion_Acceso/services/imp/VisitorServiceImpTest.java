package com.example.Gestion_Acceso.services.imp;
import com.example.Gestion_Acceso.dtos.NewAuthRangeDto;
import com.example.Gestion_Acceso.dtos.NewUserAllowedDto;
import com.example.Gestion_Acceso.dtos.NewVehicleDto;
import com.example.Gestion_Acceso.dtos.NewVisitorDto;
import com.example.Gestion_Acceso.models.*;
import com.example.Gestion_Acceso.services.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class VisitorServiceImpTest {
    @SpyBean
    private VisitorService visitorService;
    @MockBean
    private Users_AllowedService userAllowedService;
    @MockBean
    private Auth_RangesService authRangeService;
    @MockBean
    private AllowedDaysService allowedDayService;
    @MockBean
    private VehicleService vehicleService;
    @Test
    void createVisitor(){
        UserAllowed userAllowed=new UserAllowed();
        userAllowed.setId(1l);
        userAllowed.setName("Matias");
        userAllowed.setDocument("4500000");
        AuthRange authRange=new AuthRange();
        authRange.setId(1l);
        Vehicle vehicle=new Vehicle();
        vehicle.setId(5l);
        List<AllowedDay>allowedDayList=new ArrayList<>();


        NewVisitorDto visitorDto=new NewVisitorDto();

        NewAuthRangeDto authRangeDto=new NewAuthRangeDto();
        authRangeDto.setInit_date(LocalDate.now());

        NewUserAllowedDto userAllowedDto=new NewUserAllowedDto();
        userAllowedDto.setDocument("4500000");
        userAllowedDto.setName("Matias");

        NewVehicleDto vehicleDto=new NewVehicleDto();
        vehicleDto.setInsurace("ok");

        visitorDto.setNewAuthRangeDto(authRangeDto);
        visitorDto.setNewVehicleDto(vehicleDto);
        visitorDto.setNewUserAllowedDto(userAllowedDto);

        List<NewVisitorDto>list=new ArrayList<>();
        list.add(visitorDto);

        Mockito.when(userAllowedService.createUserAllowed(visitorDto.getNewUserAllowedDto())).thenReturn(userAllowed);
        Mockito.when(authRangeService.creatRange(visitorDto.getNewAuthRangeDto(),userAllowed.getId())).thenReturn(authRange);
        Mockito.when(allowedDayService.CreateAllowedDays(visitorDto.getNewAuthRangeDto().getAllowedDaysDtos(),authRange.getId())).thenReturn(allowedDayList);
        Mockito.when(vehicleService.createVehicle(visitorDto.getNewVehicleDto(),1l)).thenReturn(vehicle);

        List<UserAllowed>visitors= visitorService.crateVisitor(list);
        Assertions.assertNotNull(visitors);
        Assertions.assertEquals(visitors.get(0).getName(),list.get(0).getNewUserAllowedDto().getName());

    }

}