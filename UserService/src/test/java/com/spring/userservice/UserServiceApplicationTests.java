package com.spring.userservice;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class UserServiceApplicationTests {


    @Test
    public void add(){
        int result=UserServiceApplication.add(2,5);
        assertEquals(7, result);
    }

}
