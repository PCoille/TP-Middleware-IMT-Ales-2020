package net.coille.middleware.server.utils;

import net.coille.middleware.common.arch.ArchitectureEnum;

import java.net.MalformedURLException;
import java.rmi.RemoteException;

public interface Server {
    void serveConnection() throws RemoteException, MalformedURLException;
    ArchitectureEnum getArchitecture();
}
