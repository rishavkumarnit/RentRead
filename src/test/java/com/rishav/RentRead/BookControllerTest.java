package com.rishav.RentRead;

import com.rishav.RentRead.Controller.BookController;
import com.rishav.RentRead.Entity.Book;
import com.rishav.RentRead.Services.BookService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BookController.class)
public class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;

    @Test
    @WithMockUser(roles = "ROLE_USER")
    public void testGetAllBooks() throws Exception {
        Book book = new Book();
        book.setBookId(1L);
        book.settitle("Test Book");

        Mockito.when(bookService.getallBooks()).thenReturn(Arrays.asList(book));

        mockMvc.perform(get("/books")
                .param("email", "test@example.com")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value("Test Book"));
    }

    @Test
    @WithMockUser(roles = "ROLE_ADMIN")
    public void testCreateBook() throws Exception {
        Book book = new Book();
        book.setBookId(1L);
        book.settitle("Test Book");

        Mockito.when(bookService.create(Mockito.any(Book.class))).thenReturn(book);

        mockMvc.perform(post("/books")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"title\":\"Test Book\", \"author\":\"Test Author\", \"genre\":\"Test Genre\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.title").value("Test Book"));
    }

    
}