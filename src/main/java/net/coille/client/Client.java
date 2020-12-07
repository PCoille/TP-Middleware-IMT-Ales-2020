package net.coille.client;

import java.rmi.RemoteException;

import net.coille.client.ui.cli.CLIClient;


public class Client {

    public static void main(String[] args) throws RemoteException {
        CLIClient.main(args);
        System.exit(0);
    }

}

