package net.coille.middleware.client.utils;

import net.coille.middleware.common.message.GlobalMessageImpl;
import net.coille.middleware.common.message.Message;
import net.coille.middleware.common.message.PersonalMessageImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MessageBoxImplTest {
    MessageBoxImpl messageBox;

    @BeforeEach
    void setUp() throws RemoteException {
        messageBox = new MessageBoxImpl();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void receive() {
        assertTrue(messageBox.messageList.isEmpty());

        messageBox.receive(new PersonalMessageImpl("sender","receiver","message"));

        assertEquals(messageBox.messageList.get(0), new PersonalMessageImpl("sender","receiver","message"));
    }

    @Test
    void getMessages() {
        assertTrue(messageBox.messageList.isEmpty());

        List<Message> messageList = new ArrayList<>();
        messageList.add(new PersonalMessageImpl("u1", "u2", "Hello"));
        messageList.add(new GlobalMessageImpl("u2", "Hi everyone"));

        messageBox.messageList = messageList;

        assertEquals(messageList, messageBox.getMessages());
    }
}