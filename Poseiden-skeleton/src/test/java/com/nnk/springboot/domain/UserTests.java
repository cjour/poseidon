package com.nnk.springboot.domain;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserTests {

    @Test
    public void getters_should_return_values_set_through_setters(){
        User user = new User();

        String username = "Clément";
        String role = "USER";
        String fullname = "Clément";
        String password = "password";
        Integer id = 1;

        user.setUsername(username);
        user.setFullname(fullname);
        user.setRole(role);
        user.setPassword(password);
        user.setId(id);

        Assertions.assertEquals(user.getFullname(), fullname);
        Assertions.assertEquals(user.getRole(), role);
        Assertions.assertEquals(user.getUsername(), username);
        Assertions.assertEquals(user.getPassword(), password);
        Assertions.assertEquals(user.getId(), id);
    }
}
