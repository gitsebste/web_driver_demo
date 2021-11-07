package com.example.web_driver_demo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AuchanPromTest {

    private static AuchanProm auchanProm;

    @BeforeAll
    static void init() {

        auchanProm = new AuchanProm();
        auchanProm.getPage();
    }

    @Test
    void testGetPage() {
        boolean txtFound = auchanProm.textFound();

        Assertions.assertTrue(txtFound);
    }

    @Test
    void testLoadAllProducts() {

        auchanProm.tryToReachAllProducts();

        Assertions.assertTrue(auchanProm.AreAllProductsReachable());
    }
}