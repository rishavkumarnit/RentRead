package com.rishav.RentRead.Services;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.rishav.RentRead.Entity.*;
import java.util.*;
import com.rishav.RentRead.Exception.*;
import com.rishav.RentRead.Repository.*;



@Service
public class SecurityService {


    @Autowired
    private UserRepo userRepo;


    @Autowired
    private PasswordEncoder passwordEncoder;


    @Autowired
    private AuthenticationManager authenticationManager;



    public String registerUser(User user) {
        Optional<User> userResponse = userRepo.findByEmail(user.getEmail());
        if(!userResponse.isEmpty()){
            throw new AlreadyExistsException("User already exists");
        }
        if (userResponse.get().getrole() == null) {
            userResponse.get().setrole("ROLE_USER");
        }
        userResponse.get().setPassword(passwordEncoder.encode(user.getPassword()));
        userRepo.save(userResponse.get());
        return "User Registered Successfully!";
    }


    public String login(User user) {
        authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));
        return "User Logged in successfully!";
    }



    public User getUserById(Long id){
        Optional<User> userResponse = userRepo.findById(id);
        if(userResponse.isEmpty()){
            throw new NotfoundException("User not found");
        }
        userResponse.get().setPassword("CONFIDENTIAL");

        return userResponse.get();
    }

}