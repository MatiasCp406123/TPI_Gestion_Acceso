package com.example.Gestion_Acceso.repositories;


import com.example.Gestion_Acceso.entities.DummyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DummyRepository extends JpaRepository<DummyEntity,Long> {
}
