package com.example.Gestion_Acceso.services;

import com.example.Gestion_Acceso.dtos.NewAuthRangeDto;
import com.example.Gestion_Acceso.models.AuthRange;

public interface Auth_RangesService {
    AuthRange creatRange(NewAuthRangeDto newAuthRangeDto,Long id);
}
