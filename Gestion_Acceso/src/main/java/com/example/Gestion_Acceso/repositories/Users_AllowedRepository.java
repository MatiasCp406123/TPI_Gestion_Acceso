package com.example.Gestion_Acceso.repositories;

import com.example.Gestion_Acceso.entities.Users_AllowedEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface Users_AllowedRepository extends JpaRepository<Users_AllowedEntity,Long> {
    @Query("select u from Users_AllowedEntity u where u.document=:document")
    Users_AllowedEntity getByDocument(@Param("document")String document);
}
