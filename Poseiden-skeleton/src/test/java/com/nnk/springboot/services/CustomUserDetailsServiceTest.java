package com.nnk.springboot.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class CustomUserDetailsServiceTest {

    @Autowired
    CustomUserDetailsService customUserDetailsService;

    @Test
    public void test(){
        String username = "jeanine";
        UserDetails result = customUserDetailsService.loadUserByUsername(username);
        Assertions.assertEquals(result.getUsername(), username);
    }

    @Test
    public void test2(){
        String username = "unknown";
        Exception exception = assertThrows(RuntimeException.class, () -> {
            customUserDetailsService.loadUserByUsername(username);
        });

        String expectedMessage = "Username" + username + "was not found";
        String actualMessage = exception.getMessage();

        Assertions.assertTrue(actualMessage.contains(expectedMessage));

    }
}
