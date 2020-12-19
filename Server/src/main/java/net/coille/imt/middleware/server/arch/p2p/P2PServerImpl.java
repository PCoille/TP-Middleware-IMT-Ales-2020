package net.coille.imt.middleware.server.arch.p2p;

import net.coille.imt.middleware.common.arch.ArchitectureEnum;
import net.coille.imt.middleware.server.utils.Server;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;

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
