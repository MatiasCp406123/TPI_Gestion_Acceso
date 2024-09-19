package com.example.Gestion_Acceso.repositories.Types;

import com.example.Gestion_Acceso.entities.Document_TypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface Document_TypeRepository extends JpaRepository<Document_TypeEntity,Long> {
    @Query("SELECT d from Document_TypeEntity d where d.description=:description")
    Document_TypeEntity getByDescription(@Param("description")String description);
}
