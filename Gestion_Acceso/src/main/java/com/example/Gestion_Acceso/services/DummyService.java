package com.example.Gestion_Acceso.services;

import com.example.Gestion_Acceso.models.Dummy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DummyService {
    Dummy getDummy(Long id);
    List<Dummy>getDummyList();
    Dummy createDummy(Dummy dummy);
    Dummy updateDummy(Dummy dummy);
    void deleteDummy(Dummy dummy);
}
