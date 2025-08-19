package org.example.food_delivery_system.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.example.food_delivery_system.models.Role;

@Getter
@Setter
@ToString
public class SignUpUserRequestDto {
    private String name;
    private String phoneNumber;
    private String email;
    private String password;
    private Role role;
}
