package org.example.food_delivery_system.models;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class User extends BaseModel {
    private String name;
    private String phoneNumber;
    private String email;
    private String hashedPassword;
    @Enumerated(value = EnumType.STRING)
    private Role role;
}
