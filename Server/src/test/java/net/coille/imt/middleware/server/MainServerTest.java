package net.coille.imt.middleware.server;

import net.coille.imt.middleware.server.arch.p2p.P2PServerImpl;
import net.coille.imt.middleware.server.arch.pull.PullServerImpl;
import net.coille.imt.middleware.server.arch.push.PushServerImpl;
import org.junit.jupiter.api.Test;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class MainServerTest {
    @Test
    void generateServerFromString(){
        assertTrue(Objects.requireNonNull(MainServer.generateServerFromString("p2p")).getServer() instanceof P2PServerImpl);
        assertTrue(Objects.requireNonNull(MainServer.generateServerFromString("--p2p")).getServer() instanceof P2PServerImpl);
        assertTrue(Objects.requireNonNull(MainServer.generateServerFromString("pull")).getServer() instanceof PullServerImpl);
        assertTrue(Objects.requireNonNull(MainServer.generateServerFromString("--pull")).getServer() instanceof PullServerImpl);
        assertTrue(Objects.requireNonNull(MainServer.generateServerFromString("push")).getServer() instanceof PushServerImpl);
        assertTrue(Objects.requireNonNull(MainServer.generateServerFromString("--push")).getServer() instanceof PushServerImpl);

        assertNull(MainServer.generateServerFromString("randomTest"));
    }
}