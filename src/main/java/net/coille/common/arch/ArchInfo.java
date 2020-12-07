package net.coille.common.arch;

import net.coille.common.arch.ArchitectureEnum;

import java.net.MalformedURLException;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ArchInfo extends Remote {
    void serveArch() throws MalformedURLException, RemoteException;
    ArchitectureEnum getArchitecture() throws RemoteException;
}
