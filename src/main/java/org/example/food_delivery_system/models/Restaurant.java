package org.example.food_delivery_system.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Restaurant extends BaseModel {
//    private String name;
//    private String phoneNumber;
//    private String password;
//    private String email;
    @OneToOne
    @JoinColumn
    private User admin;
    private String name;
    private String description;
    private String phoneNumber;
    private String email;
    private double rating;
    @Enumerated(EnumType.STRING)
    @ElementCollection
    private List<Cuisine> cuisines;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn
    private Address address;
    private String image;
    @OneToOne(mappedBy = "restaurant", cascade = CascadeType.ALL, orphanRemoval = true)
    private FoodMenu foodMenu;
}
