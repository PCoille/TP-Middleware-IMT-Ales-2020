package net.coille.imt.middleware.common.message;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GlobalMessageImplTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getSender() {
        assertEquals("sender",new GlobalMessageImpl("sender","myMessage").getSender());
    }

    @Test
    void getRawMessage() {
        assertEquals("myMessage",new GlobalMessageImpl("sender","myMessage").getRawMessage());
    }

    @Test
    void testEquals() {
        GlobalMessageImpl m1 = new GlobalMessageImpl("u1", "Hi");
        GlobalMessageImpl m2 = new GlobalMessageImpl("u1", "Hi");
        GlobalMessageImpl m3 = new GlobalMessageImpl("u2", "Hi");
        GlobalMessageImpl m4 = new GlobalMessageImpl("u1", "Hi2");

        assertEquals(m1, m2);

        assertNotEquals(m1, m3);
        assertNotEquals(m1, m4);
    }
}