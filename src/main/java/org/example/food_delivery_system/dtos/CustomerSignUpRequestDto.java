package org.example.food_delivery_system.dtos;

import lombok.Getter;
import lombok.Setter;
import org.example.food_delivery_system.models.Address;
import org.example.food_delivery_system.models.Role;

import java.util.List;

@Getter
@Setter
public class CustomerSignUpRequestDto {
    private String name;
    private String phoneNumber;
    private String email;
    private String password;
    private List<Address> addresses;
}
