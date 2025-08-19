package org.example.food_delivery_system.controllers;

import org.example.food_delivery_system.dtos.SignUpUserRequestDto;
import org.example.food_delivery_system.dtos.SignUpUserResponseDto;
import org.example.food_delivery_system.exceptions.EmailAlreadyExistsException;
import org.example.food_delivery_system.exceptions.PhoneNumberAlreadyExistsException;
import org.example.food_delivery_system.models.User;
import org.example.food_delivery_system.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping("")
    public String healthCheck() {
        return "Everything's working fine!";
    }

    @PostMapping("/sign-up")
    public ResponseEntity<SignUpUserResponseDto> signUpUser(@RequestBody SignUpUserRequestDto requestDto) throws EmailAlreadyExistsException, PhoneNumberAlreadyExistsException {
        System.out.println(requestDto.toString());
        SignUpUserResponseDto responseDto = new SignUpUserResponseDto();
        User user = userService.signUpUser(requestDto.getName(), requestDto.getEmail(), requestDto.getPhoneNumber(), requestDto.getRole(), requestDto.getPassword());
        responseDto.setUserId(user.getId());

        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @ExceptionHandler({PhoneNumberAlreadyExistsException.class, EmailAlreadyExistsException.class})
    public ResponseEntity<String> handle(Exception exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.CONFLICT);
    }
}
