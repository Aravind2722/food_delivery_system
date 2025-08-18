package org.example.food_delivery_system.models;


import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class DeliveryPartner extends BaseModel {
    private String name;
    private String email;
    private String phoneNumber;
    private String password;

    @OneToOne
    private Location currentLocation;
}
