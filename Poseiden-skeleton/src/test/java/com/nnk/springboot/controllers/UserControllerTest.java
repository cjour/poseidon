package com.nnk.springboot.controllers;

import com.nnk.springboot.repositories.UserRepository;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = UserController.class)
public class UserControllerTest {

    @MockBean
    UserRepository userRepository;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    MockMvc mockMvc;

   /* @Test
    @WithMockUser
    public void test() throws Exception {
        mockMvc.perform(get("/user/list")).andExpect(status().isOk());
    }*/

}
