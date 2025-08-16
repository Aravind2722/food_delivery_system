package org.example.food_delivery_system.models;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Location extends BaseModel {
    private double longitude;
    private double latitude;
}
