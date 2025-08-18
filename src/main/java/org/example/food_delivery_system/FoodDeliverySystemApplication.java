package org.example.food_delivery_system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class FoodDeliverySystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(FoodDeliverySystemApplication.class, args);
    }

}
