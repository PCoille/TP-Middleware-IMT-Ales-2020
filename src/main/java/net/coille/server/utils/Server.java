package net.coille.server.utils;

import net.coille.common.arch.ArchitectureEnum;

import java.net.MalformedURLException;
import java.rmi.RemoteException;

public interface Server {
    void serveConnection() throws RemoteException, MalformedURLException;
    ArchitectureEnum getArchitecture();
}
