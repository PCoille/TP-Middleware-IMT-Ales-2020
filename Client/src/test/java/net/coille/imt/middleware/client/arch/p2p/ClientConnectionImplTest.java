package net.coille.imt.middleware.client.arch.p2p;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.rmi.RemoteException;

import static org.junit.jupiter.api.Assertions.*;

class ClientConnectionImplTest {
    ClientConnectionImpl clientConnection;

    @BeforeEach
    void setUp() throws RemoteException {
        clientConnection = new ClientConnectionImpl();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getClientManager() {
        assertEquals(clientConnection.clientManager, clientConnection.getClientManager());
        assertNotNull(clientConnection.getClientManager());
    }

    @Test
    void getInbox() {
        assertEquals(clientConnection.inbox, clientConnection.getInbox());
        assertNotNull(clientConnection.getInbox());
    }
}