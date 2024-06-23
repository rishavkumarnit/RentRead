package com.rishav.RentRead.Repository;


import com.rishav.RentRead.Entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;



public interface UserRepo extends JpaRepository<User, Long>{
    Optional<User> findByEmail(String email);
}
