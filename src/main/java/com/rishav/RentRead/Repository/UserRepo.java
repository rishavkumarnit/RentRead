package com.rishav.RentRead.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import com.rishav.RentRead.model.*;
import java.util.*;



public interface UserRepo extends JpaRepository<User, Long>{
    Boolean existsByEmail(String email);
    Optional<User> findByEmail(String email);
}
