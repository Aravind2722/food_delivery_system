package org.example.food_delivery_system.repositories;

import org.example.food_delivery_system.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Customer save(Customer customer);
}
