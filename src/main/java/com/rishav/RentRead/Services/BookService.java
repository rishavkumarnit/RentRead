package com.rishav.RentRead.Services;


import java.util.*;

import com.rishav.RentRead.Repository.*;
import com.rishav.RentRead.model.*;
import com.rishav.RentRead.Exception.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class BookService {


    @Autowired
    private UserRepo userRepo;


    @Autowired
    private BookRepo bookRepo;


    public List<Book> getallBooks() {
        return bookRepo.findAll();
    }


    public Book create(Book book) {
        if (book.getTitle().trim().isEmpty() || book.getAuthor().trim().isEmpty() || book.getGenre().trim().isEmpty()) {
            throw new BlankException("Title/Author/Genre can't be blank");
        }
        book.setAvailable(true);
        return bookRepo.save(book);
    }


    public String delete(long bookId) {
        Optional<Book> book = bookRepo.findById(bookId);
        if (book.isPresent()) {
            bookRepo.deleteById(bookId);
        } else {
            throw new NotfoundException("Book not found");
        }
        return "Book deleted successfully";
    }


    public Book renttheBook(String email, long bookId) {
        Optional<User> user = userRepo.findByEmail(email);
        if (!user.isPresent()) {
            throw new NotfoundException("User not found");
        }
        Optional<Book> book = bookRepo.findById(bookId);
        if (!book.isPresent()) {
            throw new NotfoundException("Book not found");
        }
        if(user.get().getBooks().size() == 2){
            throw new BooklimitException("User already have two rented books"); 
        }
        if (!book.get().getAvailable()) {
            throw new BooklimitException("Book is already rented out");
        }
        book.get().setAvailable(false);
        book.get().setUser(user.get());
        user.get().getBooks().add(book.get());
        userRepo.save(user.get());
        bookRepo.save(book.get());
        return book.get();
    }


    public Book returntheBook(String email, long bookId) {
        Optional<User> user = userRepo.findByEmail(email);
        if (!user.isPresent()) {
            throw new NotfoundException("User not found");
        }
        Optional<Book> book = bookRepo.findById(bookId);
        if (!book.isPresent()) {
            throw new NotfoundException("Book not found");
        }
        List<Book> books = user.get().getBooks();
        if (!books.contains(book.get())) {
            throw new NotfoundException("Book is not found");
        }
        
        user.get().getBooks().remove(book.get()); 
        book.get().setAvailable(true);
        book.get().setUser(null);
        userRepo.save(user.get());
        bookRepo.save(book.get());
        return book.get();
    }
    
}
