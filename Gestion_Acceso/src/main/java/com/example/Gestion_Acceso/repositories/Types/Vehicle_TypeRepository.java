package com.example.Gestion_Acceso.repositories.Types;

import com.example.Gestion_Acceso.entities.Vehicles_TypesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface Vehicle_TypeRepository extends JpaRepository<Vehicles_TypesEntity,Long> {
    @Query("select v from Vehicles_TypesEntity v where v.description=:description")
        Vehicles_TypesEntity getByDescription(@Param("description")String description);
}
