package com.example.Gestion_Acceso.controllers;

import com.example.Gestion_Acceso.dtos.NewVisitorDto;
import com.example.Gestion_Acceso.models.UserAllowed;
import com.example.Gestion_Acceso.services.VisitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class VisitorController {
    @Autowired
    private VisitorService visitorService;
    @PostMapping("/visitors")
    public ResponseEntity<List<UserAllowed>> addVisitors(@RequestBody List<NewVisitorDto> visitorDto){
        List<UserAllowed> visitors=visitorService.crateVisitor(visitorDto);
        return ResponseEntity.ok(visitors);
    }
}
