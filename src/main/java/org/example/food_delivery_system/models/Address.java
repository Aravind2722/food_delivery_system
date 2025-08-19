package org.example.food_delivery_system.models;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Address extends BaseModel {
    private String buildingName;
    private String buildingNumber;
    private String street;
    private String area;
    private String city;
    private String pincode;
    private String state;


    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private Location location;
}
