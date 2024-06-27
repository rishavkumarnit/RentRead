package com.rishav.RentRead.Controller;


import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import com.rishav.RentRead.Services.BookService;
import com.rishav.RentRead.model.Book;
import lombok.extern.slf4j.Slf4j;



@RestController
@Slf4j
@RequestMapping("/books")
public class BookController {


    @Autowired
    private BookService bookService;


    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks() {
        log.info("Getting all books");
        List<Book> books = bookService.getallBooks();
        log.info("Books fetched successfully");
        return new ResponseEntity<>(books, HttpStatus.OK);
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Book create(@RequestBody Book book) {
        log.info("Admin is attempting to create a new book with ID: {}", book.getId());
        Book createdbook = bookService.create(book);
        log.info("Admin has created a new book with ID: {}", createdbook.getId());
        return createdbook;

    }


    @PutMapping("/{bookId}/rent")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("isAuthenticated")
    public Book rentBook( @PathVariable long bookId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = ((UserDetails) authentication.getPrincipal()).getUsername();
        log.info("User {} is attempting to rent book with ID: {}", email, bookId);
        Book rentedbook =  bookService.renttheBook(email, bookId);
        log.info("User {} has successfully rented the book with ID: {}", email, bookId);
        return rentedbook;
    }


    @PutMapping("/{bookId}/return")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("isAuthenticated")
    public Book returnBook( @PathVariable long bookId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = ((UserDetails) authentication.getPrincipal()).getUsername();
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
