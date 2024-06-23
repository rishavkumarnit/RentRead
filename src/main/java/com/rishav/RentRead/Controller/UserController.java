package com.rishav.RentRead.Controller;


import com.rishav.RentRead.Entity.*;
import com.rishav.RentRead.Services.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {


    private final SecurityService securityService;


    @Autowired
    public UserController(SecurityService securityService) {
        this.securityService = securityService;
    }


    @PostMapping("/register")
    @ResponseStatus(HttpStatus.OK)
    public String registerUser(@RequestBody User user) {
        log.info("Registering user: {}", user.getEmail());
        securityService.registerUser(user);
        log.info("User registered successfully: {}", user.getEmail());
        return "User registered successfully";
    }


    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public String login(@RequestBody  User user) {
        log.info("Logging in user: {}", user.getEmail());
        securityService.login(user);
        log.info("User logged in successfully: {}", user.getEmail());
        return "User login successfull";
    }


    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public User getUserById(@PathVariable("id") Long id) {
        log.info("Received GET request for user with id: {}", id);
        User user = securityService.getUserById(id);
        log.info("Responding with status code: {}", HttpStatus.CREATED);
        return user;
    }
}