package com.example.Gestion_Acceso.controllers;

import com.example.Gestion_Acceso.dtos.DummyDto;
import com.example.Gestion_Acceso.services.DummyService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dummy")
public class DummyController {
    @Autowired
    private DummyService dummyService;


    @GetMapping("")
    public ResponseEntity<DummyDto> getDummyList(){
        List<Dummy>dummyList=dummyService.getDummyList();
        return  null;
    }
    @GetMapping("/{id}")
    public ResponseEntity<DummyDto>getDummyList(@PathVariable Long id){
        dummyService.getDummy(id);
        return null;
    }
    @PostMapping("")
    public ResponseEntity<DummyDto>createDummy(@RequestBody DummyDto dummyDto){
        Dummy dummy= dummyService.createDummy(null);
        return null;
    }

    @PutMapping("")
    public ResponseEntity<DummyDto>updateDummy(DummyDto dummyDto){
        Dummy dummy=dummyService.updateDummy(null);
        return null;
    }
    @DeleteMapping("")
    public ResponseEntity<DummyDto>deleteDummy(DummyDto dummyDto){
        dummyService.deleteDummy(null);
        return null;
    }

}
