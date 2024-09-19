package com.example.Gestion_Acceso.repositories;

import com.example.Gestion_Acceso.entities.Auth_RangeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Auth_RangesRepository extends JpaRepository<Auth_RangeEntity,Long> {
}
