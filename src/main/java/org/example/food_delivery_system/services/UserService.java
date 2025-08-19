package org.example.food_delivery_system.services;

import org.example.food_delivery_system.exceptions.EmailAlreadyExistsException;
import org.example.food_delivery_system.exceptions.PhoneNumberAlreadyExistsException;
import org.example.food_delivery_system.models.Role;
import org.example.food_delivery_system.models.User;
import org.springframework.stereotype.Service;

public interface UserService {
    User signUpUser(String name, String email, String phoneNumber, Role role, String password) throws PhoneNumberAlreadyExistsException, EmailAlreadyExistsException;
}
