package com.rishav.RentRead.Repository;


import com.rishav.RentRead.Entity.*;
import org.springframework.data.jpa.repository.JpaRepository;



public interface BookRepo extends JpaRepository<Book, Long>{

}
