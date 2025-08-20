package org.example.food_delivery_system.services;

import org.example.food_delivery_system.dtos.CustomerSignUpResponseDto;
import org.example.food_delivery_system.exceptions.EmailAlreadyExistsException;
import org.example.food_delivery_system.exceptions.PhoneNumberAlreadyExistsException;
import org.example.food_delivery_system.models.Address;
import org.example.food_delivery_system.models.Customer;
import org.example.food_delivery_system.models.Role;
import org.example.food_delivery_system.models.User;
import org.example.food_delivery_system.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {
    private UserService userService;
    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImpl(OneRoleOneUserService userService,
                               CustomerRepository customerRepository) {
        this.userService = userService;
        this.customerRepository = customerRepository;
    }
    @Override
    public Customer signUpCustomer(String name, String email, String phoneNumber, String password, List<Address> addresses) throws EmailAlreadyExistsException, PhoneNumberAlreadyExistsException {
        User user = userService.signUpUser(name, email, phoneNumber, Role.CUSTOMER, password);

        Customer customer = new Customer();
        customer.setUser(user);
        customer.setAddresses(addresses);
        return customerRepository.save(customer);
    }
}
