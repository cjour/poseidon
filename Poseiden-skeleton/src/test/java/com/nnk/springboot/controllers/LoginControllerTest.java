package com.nnk.springboot.controllers;

import com.nnk.springboot.repositories.UserRepository;
import com.nnk.springboot.services.TradeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = LoginController.class)
public class LoginControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    UserRepository userRepository;

    @Test
    public void test3() throws Exception {
        mockMvc.perform(get("/login")).andExpect(status().isOk());
    }

    @Test
    public void test4() throws Exception {
        mockMvc.perform(get("/secure/article-details")).andExpect(status().isUnauthorized());
    }

    @Test
    public void test5() throws Exception {
        mockMvc.perform(get("/error")).andExpect(status().isUnauthorized());
    }
}
