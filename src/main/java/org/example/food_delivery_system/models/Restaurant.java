package org.example.food_delivery_system.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Restaurant extends BaseModel {
    private String name;
    private String description;
    private String phoneNumber;
    private String password; //check if correct
    private String email;
    private double rating;
    @Enumerated(EnumType.STRING)
    @ElementCollection
    private List<Cuisine> cuisines;

    @OneToOne
    private Address address;
    private String image;
    @OneToOne(mappedBy = "restaurant")
    private FoodMenu foodMenu;
}
