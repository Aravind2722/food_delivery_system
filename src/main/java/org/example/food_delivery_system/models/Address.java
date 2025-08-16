package org.example.food_delivery_system.models;


import jakarta.persistence.OneToOne;

public class Address extends BaseModel {
    private String buildingName;
    private String buildingNumber;
    private String street;
    private String area;
    private String city;
    private String pincode;
    private String state;


    @OneToOne
    private Location location;
}
