package org.example.food_delivery_system.models;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Payment extends BaseModel {
    @ManyToOne
    private Order order;
    @Enumerated(value = EnumType.STRING)
    private PaymentMode paymentMode;
    @Enumerated(value = EnumType.STRING)
    private PaymentProvider paymentProvider;
    @Enumerated(value = EnumType.STRING)
    private PaymentStatus paymentStatus;

    private double amount;

}
