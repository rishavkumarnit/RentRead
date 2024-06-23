package com.rishav.RentRead.Controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.rishav.RentRead.Entity.Book;
import com.rishav.RentRead.Services.BookService;

import lombok.extern.slf4j.Slf4j;



@RestController
@Slf4j
@RequestMapping("/books")
public class BookController {


    @Autowired
    private BookService bookService;


    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Book> getallbooks(@RequestParam String email){
        log.info("User {} is attempting to get list of all books", email);
        return bookService.getallBooks();
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Book create(@RequestBody Book book) {
        log.info("Admin is attempting to create a new book with ID: {}", book.getBookId());
        Book createdbook = bookService.create(book);
        log.info("Admin has created a new book with ID: {}", createdbook.getBookId());
        return createdbook;

    }


    @PostMapping("/{bookId}/rent")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("isAuthenticated")
    public Book rentBook(@RequestParam String email, @PathVariable long bookId) {
        log.info("User {} is attempting to rent book with ID: {}", email, bookId);
        Book rentedbook =  bookService.renttheBook(email, bookId);
        log.info("User {} has successfully rented the book with ID: {}", email, bookId);
        return rentedbook;
    }


    @PostMapping("/{bookId}/return")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("isAuthenticated")
    public Book returnBook(@RequestParam String email, @PathVariable long bookId) {
        log.info("User {} is attempting to return book with ID: {}", email, bookId);
        Book returnedbook =  bookService.returntheBook(email, bookId);
        log.info("User {} is attempting to return book with ID: {}", email, bookId);
        return returnedbook;
    }


    @DeleteMapping("/{bookId}")
    @ResponseStatus(HttpStatus.OK)
    public String delete(@PathVariable long bookId) {
        log.info("Admin is attempting to delete the book with ID: {}", bookId);
        String response = bookService.delete(bookId);
        log.info("Admin has deleted the book with ID: {}", bookId);
        return response;
    }
    
}
