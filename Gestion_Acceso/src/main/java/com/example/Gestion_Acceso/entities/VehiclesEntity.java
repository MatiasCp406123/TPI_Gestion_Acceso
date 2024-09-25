package com.example.Gestion_Acceso.entities;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "vehicles")
@Data
public class VehiclesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JoinColumn(name = "vehicle_type_id")
    private Long vehicleType;
    @ManyToOne
    @JoinColumn(name = "user_allowed_id")
    private Users_AllowedEntity users_allowedId;
    private String plate;

    private String insurace;
    @Column(name = "created_user")
    private Integer createdUser;

    @Column(name = "created_date")
    private Date createdDate;

    @Column(name = "last_updated_user")
    private Integer lastUpdatedUser;

    @Column(name = "last_updated_date")
    private Date lastUpdatedDate;
}
