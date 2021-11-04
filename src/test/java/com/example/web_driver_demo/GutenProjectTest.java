package com.example.web_driver_demo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.LinkedList;
import java.util.List;

@SpringBootTest
public class GutenProjectTest {

    private static AuchanProm auchanProm;

    @BeforeAll
    static void init() {

        GutenProject gutenProject = new GutenProject();
    }

    @Test
    void testNothing() {
    }
}