package com.example.Gestion_Acceso.repositories;

import com.example.Gestion_Acceso.entities.Allowed_DaysEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Allowed_daysRepository extends JpaRepository<Allowed_DaysEntity,Long> {
}
