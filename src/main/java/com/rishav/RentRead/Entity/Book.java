package com.rishav.RentRead.Entity;


import jakarta.persistence.*;


@Entity
public class Book {
    

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookId;
    private String title;
    private String author;
    private String genre;
    private boolean available;


    public Book(){

    }


    public long getBookId(){
        return this.bookId;
    }


    public void setBookId(long bookId){
        this.bookId = bookId;
    }


    public String gettitle(){
        return this.title;
    }


    public void settitle(String title){
        this.title = title;
    }


    public void setauthor(String author){
        this.author = author;
    }


    public String getauthor(){
        return this.author;
    }


    public void setgenre(String genre){
        this.genre = genre;
    }


    public String getgenre(){
        return this.genre;
    }


    public void setavailablity(boolean available){
        this.available = available;
    }
    

    public boolean getavailablity(){
        return this.available;
    }
}
