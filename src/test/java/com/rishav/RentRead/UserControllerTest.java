package com.rishav.RentRead;

import com.rishav.RentRead.Controller.*;
import com.rishav.RentRead.Entity.User;
import com.rishav.RentRead.Services.SecurityService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SecurityService securityService;

    @Test
    public void testRegisterUser() throws Exception {
        User user = new User();
        user.setEmail("test@example.com");
        user.setPassword("password");

        Mockito.doNothing().when(securityService).registerUser(Mockito.any(User.class));

        mockMvc.perform(post("/users/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"email\":\"test@example.com\",\"password\":\"password\"}"))
                .andExpect(status().isOk())
                .andExpect(content().string("User registered successfully"));
    }

    @Test
    public void testLoginUser() throws Exception {
        User user = new User();
        user.setEmail("test@example.com");
        user.setPassword("password");

        Mockito.doNothing().when(securityService).login(Mockito.any(User.class));

        mockMvc.perform(post("/users/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"email\":\"test@example.com\",\"password\":\"password\"}"))
                .andExpect(status().isOk())
                .andExpect(content().string("User login successfull"));
    }

    @Test
    public void testGetUserById() throws Exception {
        User user = new User();
        user.setUserId(1L);
        user.setEmail("test@example.com");

        Mockito.when(securityService.getUserById(1L)).thenReturn(user);

        mockMvc.perform(get("/users/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value("test@example.com"));
    }
}