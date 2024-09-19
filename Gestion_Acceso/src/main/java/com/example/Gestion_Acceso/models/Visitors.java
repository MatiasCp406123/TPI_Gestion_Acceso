package com.example.Gestion_Acceso.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Visitors {
    private UserAllowed userAllowed;
    private AuthRange authRange;
}
