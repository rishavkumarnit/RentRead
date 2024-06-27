package com.rishav.RentRead.Repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.rishav.RentRead.model.*;



public interface BookRepo extends JpaRepository<Book, Long>{

}
