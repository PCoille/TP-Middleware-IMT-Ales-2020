package net.coille.imt.middleware.client.ui;

import net.coille.imt.middleware.common.message.GlobalMessageImpl;
import net.coille.imt.middleware.common.message.PersonalMessageImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MockUIControllerTest {
    MockUIController mockUIController;

    @BeforeEach
    void setUp() {
        mockUIController = new MockUIController();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void initSystem() throws RemoteException, NotBoundException, MalformedURLException {
        mockUIController.initSystem();
        assertTrue(true);
    }

    @Test
    void initUser() throws RemoteException {
        assertTrue(mockUIController.initUser("testName"));
        assertEquals("testName", mockUIController.username);
    }

    @Test
    void exit() throws RemoteException {
        mockUIController.exit(mockUIController.username);
        assertTrue(true);
    }

    @Test
    void getMessages() throws RemoteException {
        assertTrue(mockUIController.messages.isEmpty());

        mockUIController.messages.add(new GlobalMessageImpl("test", "testMsg"));

        assertFalse(mockUIController.messages.isEmpty());
        assertEquals(new GlobalMessageImpl("test", "testMsg"), mockUIController.getMessages().get(0));
    }

    @Test
    void sendPersonalMessage() throws RemoteException {
        assertTrue(mockUIController.messages.isEmpty());

        mockUIController.sendPersonalMessage(new PersonalMessageImpl("send", "rec", "msg"));

        assertEquals(new PersonalMessageImpl("send", "rec", "msg"), mockUIController.messages.get(0));
    }

    @Test
    void sendMessageToAll() throws RemoteException {
        assertTrue(mockUIController.messages.isEmpty());

        mockUIController.sendMessageToAll(new GlobalMessageImpl("test", "testMsg"));

        assertEquals(new GlobalMessageImpl("test", "testMsg"), mockUIController.messages.get(0));
    }

    @Test
    void getUsers() throws RemoteException {
        assertTrue(mockUIController.users.isEmpty());

        List<String> users = new ArrayList<>();
        users.add("u1");
        users.add("u2");
        users.add("u3");

        mockUIController.users.addAll(users);

        assertEquals(3, mockUIController.getUsers().size());
    }
}