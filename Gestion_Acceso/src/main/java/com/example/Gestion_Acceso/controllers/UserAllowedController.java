package com.example.Gestion_Acceso.controllers;

import com.example.Gestion_Acceso.models.UserAllowed;
import com.example.Gestion_Acceso.services.impl.UserAllowedServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/users-allowed")
public class UserAllowedController {

    private final UserAllowedServiceImpl userAllowedService;

    public UserAllowedController(UserAllowedServiceImpl userAllowedService) {
        this.userAllowedService = userAllowedService;
    }

    @GetMapping
    public ResponseEntity<List<UserAllowed>> getAllUsers() {
        try {
            return ResponseEntity.ok(userAllowedService.getAllUsers());
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
