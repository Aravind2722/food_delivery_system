package org.example.food_delivery_system.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@Entity
public class Customer extends BaseModel {
//    private String name;
//    private String phoneNumber;
//    private String email;
//    private String password;
    @OneToOne
    @JoinColumn
    private User user;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Address> addresses;
}
