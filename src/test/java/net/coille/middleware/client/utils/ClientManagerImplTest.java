package net.coille.middleware.client.utils;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ClientManagerImplTest {
    ClientManagerImpl clientManager;

    @BeforeEach
    void setUp() throws RemoteException {
        clientManager = new ClientManagerImpl();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void initClients() {
        assertTrue(clientManager.clientsList.isEmpty());

        List<String> testClientList = new ArrayList<>();
        testClientList.add("a");
        testClientList.add("b");
        testClientList.add("c");
        clientManager.initClients(testClientList);

        assertEquals(testClientList, clientManager.clientsList);
    }

    @Test
    void addClient() {
        assertTrue(clientManager.clientsList.isEmpty());

        clientManager.addClient("Test");

        assertEquals("Test", clientManager.clientsList.get(0));
    }

    @Test
    void rmClient() {
        assertTrue(clientManager.clientsList.isEmpty());

        List<String> testClientList = new ArrayList<>();
        testClientList.add("a");
        testClientList.add("b");
        testClientList.add("c");

        clientManager.clientsList = testClientList;

        clientManager.rmClient("b");

        assertEquals(2, clientManager.clientsList.size());
        assertFalse(clientManager.clientsList.contains("b"));
    }

    @Test
    void getClients() {
        assertTrue(clientManager.clientsList.isEmpty());

        List<String> testClientList = new ArrayList<>();
        testClientList.add("a");
        testClientList.add("b");
        testClientList.add("c");

        clientManager.clientsList = testClientList;

        assertEquals(testClientList, clientManager.getClients());
    }
}