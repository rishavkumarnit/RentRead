package com.rishav.RentRead.Entity;



import java.util.*;
import jakarta.persistence.*;


@Entity
public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userId;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String role;


    public User(){

    }


    @OneToMany
    @JoinTable(
        name = "books_with_users",
        joinColumns = @JoinColumn(name = "user_Id"),
        inverseJoinColumns = @JoinColumn(name = "book_id")
    )
    private List<Book> addedBooks = new ArrayList<Book>();

    
    public long getUserid(){
        return this.userId;
    }


    public void setUserId(long userId){
        this.userId = userId;
    }


    public List<Book> getaddedBooks(){
        return this.addedBooks;
    }


    public String getEmail(){
        return this.email;
    }


    public void setEmail(String email){
        this.email = email;
    }


    public void setPassword(String password){
        this.password = password;
    }


    public String getPassword(){
        return this.password;
    }


    public void setfirstName(String firstName){
        this.firstName = firstName;
    }


    public String getfirstName(){
        return this.firstName;
    }
    
    
    public void  setlastName(String lastName){
        this.lastName = lastName;
    }


    public String getlastName(){
        return this.lastName;
    }
    
    
    public void setrole(String role){
        this.role = role;
    }


    public String getrole(){
        return this.role;
    }
}