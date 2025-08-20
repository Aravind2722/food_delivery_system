package org.example.food_delivery_system.services;

import org.example.food_delivery_system.exceptions.EmailAlreadyExistsException;
import org.example.food_delivery_system.exceptions.PhoneNumberAlreadyExistsException;
import org.example.food_delivery_system.models.Address;
import org.example.food_delivery_system.models.Customer;

import java.util.List;

public interface CustomerService {
    Customer signUpCustomer(String name, String email, String phoneNumber, String password, List<Address> addresses) throws EmailAlreadyExistsException, PhoneNumberAlreadyExistsException;
}
