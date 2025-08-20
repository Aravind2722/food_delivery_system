package org.example.food_delivery_system.controllers;

import org.example.food_delivery_system.dtos.CustomerSignUpRequestDto;
import org.example.food_delivery_system.dtos.CustomerSignUpResponseDto;
import org.example.food_delivery_system.exceptions.EmailAlreadyExistsException;
import org.example.food_delivery_system.exceptions.PhoneNumberAlreadyExistsException;
import org.example.food_delivery_system.models.Customer;
import org.example.food_delivery_system.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    private CustomerService customerService;
    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/sign-up")
    public ResponseEntity<CustomerSignUpResponseDto> signUpCustomer(@RequestBody CustomerSignUpRequestDto requestDto) throws EmailAlreadyExistsException, PhoneNumberAlreadyExistsException {
        Customer customer = customerService.signUpCustomer(requestDto.getName(), requestDto.getEmail(), requestDto.getPhoneNumber(), requestDto.getPassword(), requestDto.getAddresses());
        CustomerSignUpResponseDto responseDto = new CustomerSignUpResponseDto();
        responseDto.setCustomerId(customer.getId());
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }
    @ExceptionHandler({PhoneNumberAlreadyExistsException.class, EmailAlreadyExistsException.class})
    public ResponseEntity<String> handle(Exception exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.CONFLICT);
    }
}
