package com.dr.bookcatalog;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class AppTest {
    @Test
    public void testAppHasAGreeting() {
        // class under test is App
        // App classUnderTest = new App();
        assertNotNull("app should have a greeting", App.greetWorld());
        assertTrue(App.greetWorld().contains("Hello World!"));
    }
}
