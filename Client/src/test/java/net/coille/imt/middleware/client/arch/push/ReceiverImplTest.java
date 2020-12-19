package net.coille.imt.middleware.client.arch.push;

import net.coille.imt.middleware.common.message.GlobalMessageImpl;
import net.coille.imt.middleware.common.message.Message;
import net.coille.imt.middleware.common.message.PersonalMessageImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ReceiverImplTest {
    ReceiverImpl receiver;

    @BeforeEach
    void setUp() throws RemoteException {
        receiver = new ReceiverImpl();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void receive() throws RemoteException {
        assertTrue(receiver.messageBox.getMessages().isEmpty());

        PersonalMessageImpl message = new PersonalMessageImpl("u1", "u2", "Hi");

        receiver.receive(message);

        assertEquals(message, receiver.messageBox.getMessages().get(0));
    }

    @Test
    void initClients() throws RemoteException {
        assertTrue(receiver.clientManager.getClients().isEmpty());

        List<String> testClientList = new ArrayList<>();
        testClientList.add("a");
        testClientList.add("b");
        testClientList.add("c");
        receiver.initClients(testClientList);

        assertEquals(testClientList, receiver.clientManager.getClients());
    }

    @Test
    void addClient() throws RemoteException {
        assertTrue(receiver.clientManager.getClients().isEmpty());

        receiver.addClient("Test");

        assertEquals("Test", receiver.clientManager.getClients().get(0));
    }

    @Test
    void rmClient() throws RemoteException {
        assertTrue(receiver.clientManager.getClients().isEmpty());

        List<String> testClientList = new ArrayList<>();
        testClientList.add("a");
        testClientList.add("b");
        testClientList.add("c");

        receiver.clientManager.getClients().addAll(testClientList);

        receiver.rmClient("b");

        assertEquals(2, receiver.clientManager.getClients().size());
        assertFalse(receiver.clientManager.getClients().contains("b"));
    }

    @Test
    void getMessages() {
        assertTrue(receiver.messageBox.getMessages().isEmpty());

        List<Message> messageList = new ArrayList<>();
        messageList.add(new PersonalMessageImpl("u1", "u2", "Hello"));
        messageList.add(new GlobalMessageImpl("u2", "Hi everyone"));

        receiver.messageBox.receive(messageList.get(0));
        receiver.messageBox.receive(messageList.get(1));

        assertEquals(messageList, receiver.getMessages());
    }

    @Test
    void getClients() {
        assertTrue(receiver.messageBox.getMessages().isEmpty());

        List<String> testClientList = new ArrayList<>();
        testClientList.add("a");
        testClientList.add("b");
        testClientList.add("c");

        receiver.clientManager.getClients().addAll(testClientList);

        assertEquals(testClientList, receiver.getClients());
    }
}