package com.rishav.RentRead.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.rishav.RentRead.Controller.exchanges.*;
import com.rishav.RentRead.model.*;
import com.rishav.RentRead.Services.*;
import lombok.extern.slf4j.Slf4j;



@RestController
@Slf4j
@RequestMapping("/users")
public class AuthController {


    @Autowired
    AuthService authService;


    @PutMapping("/register")
    @ResponseStatus(HttpStatus.OK)
    public String register( @RequestBody RegisterRequest request ) {
        log.info("Registering user: {}", request);
        User registeredUser = authService.register(request);
        log.info("User registered successfully: {}", registeredUser);
        return "User registered successfully";
    }


    @PutMapping("/login")
    public String login( @RequestBody AuthRequest request ) {
        log.info("Logging in user: {}", request.getEmail());
        String token = authService.login(request);
        log.info("User logged in successfully: {}", request.getEmail());
        return token;
    }

}
