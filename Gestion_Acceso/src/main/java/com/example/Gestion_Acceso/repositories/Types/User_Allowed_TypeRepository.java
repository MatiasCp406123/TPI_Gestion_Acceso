package com.example.Gestion_Acceso.repositories.Types;

import com.example.Gestion_Acceso.entities.Users_allowed_typesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface User_Allowed_TypeRepository extends JpaRepository<Users_allowed_typesEntity,Long> {
    @Query("select u from Users_allowed_typesEntity u where u.description=:description")
    Users_allowed_typesEntity getByDescription(@Param("description")String description);
}
