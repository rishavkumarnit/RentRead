package com.rishav.RentRead;

import com.rishav.RentRead.Controller.BookController;
import com.rishav.RentRead.Services.BookService;
import com.rishav.RentRead.model.Book;
import com.rishav.RentRead.Exception.*;
import com.rishav.RentRead.Controller.*;
import com.rishav.RentRead.Services.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;



class ControllerTest {


    @Mock
    private BookService bookService;


    @InjectMocks
    private BookController bookController;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void createBook_success() {
        Book request = new Book();
        Book book = new Book();
        when(bookService.create(any(Book.class))).thenReturn(book);
        Book response = bookController.create(request);
        assertEquals(book, response);
        verify(bookService, times(1)).create(request);
    }


    @Test
    void getAllBooks_success1() {
        List<Book> books = new ArrayList<>();
        when(bookService.getallBooks()).thenReturn(books);
        ResponseEntity<List<Book>> response = bookController.getAllBooks();
        assertEquals(books, response.getBody());
        verify(bookService, times(1)).getallBooks();
    }


    @Test
    void getAllBooks_success2() {
        List<Book> books = new ArrayList<>();
        when(bookService.getallBooks()).thenReturn(books);
        ResponseEntity<List<Book>> response = bookController.getAllBooks();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(bookService, times(1)).getallBooks();
    }


}