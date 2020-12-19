package net.coille.imt.middleware.common.message;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PersonalMessageImplTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getSender() {
        PersonalMessageImpl message = new PersonalMessageImpl("sender", "receiver","MyMessage");

        assertEquals("sender", message.getSender());
    }

    @Test
    void getRawMessage() {
        PersonalMessageImpl message = new PersonalMessageImpl("sender", "receiver","MyMessage");

        assertEquals("MyMessage", message.getRawMessage());
    }

    @Test
    void getReceiver() {
        PersonalMessageImpl message = new PersonalMessageImpl("sender", "receiver","MyMessage");

        assertEquals("receiver", message.getReceiver());
    }

    @Test
    void senderIsReceiver() {
        PersonalMessageImpl message = new PersonalMessageImpl("sender", "receiver","MyMessage");
        assertFalse(message.senderIsReceiver());

        PersonalMessageImpl message2 = new PersonalMessageImpl("u1", "u1","MyMessage2");
        assertTrue(message2.senderIsReceiver());
    }

    @Test
    void testEquals() {
        PersonalMessageImpl message = new PersonalMessageImpl("sender", "receiver","MyMessage");
        PersonalMessageImpl message2 = new PersonalMessageImpl("sender", "receiver","MyMessage");
        PersonalMessageImpl message3 = new PersonalMessageImpl("sender2", "receiver","MyMessage");
        PersonalMessageImpl message4 = new PersonalMessageImpl("sender", "receiver2","MyMessage");
        PersonalMessageImpl message5 = new PersonalMessageImpl("sender", "receiver","MyMessage2");

        assertEquals(message, message2);

        assertNotEquals(message, message3);
        assertNotEquals(message, message4);
        assertNotEquals(message, message5);
    }
}