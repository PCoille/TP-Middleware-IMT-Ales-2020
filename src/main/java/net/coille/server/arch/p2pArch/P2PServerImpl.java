package net.coille.server.arch.p2pArch;

import net.coille.common.arch.ArchitectureEnum;
import net.coille.server.utils.Server;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class P2PServerImpl implements Server {
    @Override
    public void serveConnection() throws RemoteException, MalformedURLException {
        ServerConnectionImpl connection = new ServerConnectionImpl();
        Naming.rebind("rmi://localhost:12357/Connection", connection);
    }

    @Override
    public ArchitectureEnum getArchitecture() {
        return ArchitectureEnum.P2P_ARCH;
    }
}
