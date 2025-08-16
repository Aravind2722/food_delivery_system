package org.example.food_delivery_system.models;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@Entity
public class Customer extends BaseModel {
    private String name;
    private String phoneNumber;
    private String email;
    private String password;
    @OneToMany
    private List<Address> addresses;
}
