package net.coille.imt.middleware.server.arch.pull;

import net.coille.imt.middleware.common.arch.ArchitectureEnum;
import net.coille.imt.middleware.server.utils.Server;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;

public class PullServerImpl implements Server {
    @Override
    public void serveConnection() throws RemoteException, MalformedURLException {
        ConnectionImpl connection = new ConnectionImpl();
        Naming.rebind("rmi://localhost:12357/Connection", connection);
    }

    @Override
    public ArchitectureEnum getArchitecture() {
        return ArchitectureEnum.PULL_ARCH;
    }
}
