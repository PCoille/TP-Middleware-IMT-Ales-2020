package net.coille.middleware.client.ui.cli;

import net.coille.middleware.client.ui.MockUIController;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CLIClientTest {
    CLIClient client;

    ByteArrayInputStream toInputStream(String input) {
        return new ByteArrayInputStream(input.getBytes());
    }

    void setInputStream(String input) {
        client.inputStream = toInputStream(input);
    }

    @BeforeEach
    void setUp() throws RemoteException, NotBoundException, MalformedURLException {

    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void initUsername() throws IOException {
        InputStream sysInBackup = System.in;

        ArrayList<String> inputList1 = new ArrayList<>(Collections.singletonList("test1"));
        ArrayList<String> inputList2 = new ArrayList<>(Arrays.asList("admin","test2","test3"));

        ByteArrayInputStream in = toInputStream(String.join(System.lineSeparator(),inputList1));
        ByteArrayInputStream in2 = toInputStream(String.join(System.lineSeparator(),inputList2));

        System.setIn(in);

        client = new CLIClient(new MockUIController());

        assertEquals("test1", client.username);

        client.inputStream = in2;

        client.initUsername();

        assertEquals("test2", client.username);
    }
}