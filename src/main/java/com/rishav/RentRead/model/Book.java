package com.rishav.RentRead.model;



import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;





@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Book {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String author;
    private String genre;
    private Boolean available;

    
    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;
}