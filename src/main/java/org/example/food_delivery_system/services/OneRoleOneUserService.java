package org.example.food_delivery_system.services;

import org.example.food_delivery_system.exceptions.EmailAlreadyExistsException;
import org.example.food_delivery_system.exceptions.PhoneNumberAlreadyExistsException;
import org.example.food_delivery_system.models.Cart;
import org.example.food_delivery_system.models.Role;
import org.example.food_delivery_system.models.User;
import org.example.food_delivery_system.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OneRoleOneUserService implements UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public OneRoleOneUserService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public User signUpUser(String name, String email, String phoneNumber, Role role, String password) throws PhoneNumberAlreadyExistsException, EmailAlreadyExistsException {
        Optional<User> userWithPhoneNumber = userRepository.findByPhoneNumber(phoneNumber);
        if (userWithPhoneNumber.isPresent()) throw new PhoneNumberAlreadyExistsException("Phone number "+phoneNumber+" already exits!");
        Optional<User> userWithEmail = userRepository.findByEmail(email);
        if (userWithEmail.isPresent()) throw new EmailAlreadyExistsException("Email "+email+" already exists!");

        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setRole(role);
        user.setPhoneNumber(phoneNumber);
        user.setHashedPassword(bCryptPasswordEncoder.encode(password));

        return userRepository.save(user);
    }
}
