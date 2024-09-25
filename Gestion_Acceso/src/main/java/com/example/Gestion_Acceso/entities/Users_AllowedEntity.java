    package com.example.Gestion_Acceso.entities;

    import jakarta.persistence.*;
    import jakarta.validation.constraints.Email;
    import jakarta.validation.constraints.NotBlank;
    import lombok.AllArgsConstructor;
    import lombok.Data;
    import lombok.NoArgsConstructor;

    import java.time.LocalDateTime;

    @Data
    @Entity
    @AllArgsConstructor
    @NoArgsConstructor
    @Table(name = "users_allowed")
    public class Users_AllowedEntity {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        @Column(unique = true, nullable = false)
        private String document;


        @JoinColumn(name = "users_type_id")
        private Long userType;


        @JoinColumn(name = "document_type_id")
        private Long documentType;
        private String name;
        private Integer created_user;
        private LocalDateTime created_date;
        private Integer last_updated_user;
        private LocalDateTime last_updated_date;

        @NotBlank(message = "El email no puede estar vacío")
        @Email(message = "Debe ser una dirección de email válida")
        @Column(name = "email", length = 255, nullable = false, unique = true)
        private String email;

        @Column(nullable = false, columnDefinition = "BOOLEAN DEFAULT FALSE")
        private Boolean emailSent = false;
    }
