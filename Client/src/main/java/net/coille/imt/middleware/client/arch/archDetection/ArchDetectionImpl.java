package net.coille.imt.middleware.client.arch.archDetection;

import net.coille.imt.middleware.client.arch.p2p.P2PClient;
import net.coille.imt.middleware.client.arch.pull.PullClient;
import net.coille.imt.middleware.client.arch.push.PushClient;
import net.coille.imt.middleware.client.ui.UIController;
import net.coille.imt.middleware.common.arch.ArchInfo;
import net.coille.imt.middleware.common.arch.ArchitectureEnum;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class ArchDetectionImpl implements ArchDetection {
    ArchitectureEnum architecture;

    public ArchDetectionImpl() throws RemoteException, NotBoundException, MalformedURLException {
        ArchInfo archInfo = (ArchInfo) Naming.lookup("rmi://localhost:12357/ArchInfo");
        architecture = archInfo.getArchitecture();
    }


    @Override
    public ArchitectureEnum getServerArchitecture() {
        return architecture;
    }

    @Override
    public UIController getUIControllerClient() {
        switch (architecture){
            case PULL_ARCH:
                return new PullClient();

            case PUSH_ARCH:
                return new PushClient();

            case P2P_ARCH:
                return new P2PClient();

            default:
                System.out.println("Architecture info not found. Loading default config.");
                return new PushClient();
        }
    }
}
