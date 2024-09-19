package com.example.Gestion_Acceso.repositories.Types;

import com.example.Gestion_Acceso.entities.Vehicles_typesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface Vehicle_TypeRepository extends JpaRepository<Vehicles_typesEntity,Long> {
    @Query("select v from Vehicles_typesEntity v where v.description=:description")
        Vehicles_typesEntity getByDescription(@Param("description")String description);
}
