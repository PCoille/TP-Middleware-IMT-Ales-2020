package net.coille.common.arch;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArchitectureEnumTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getArchName() {
        assertEquals("Pull", ArchitectureEnum.PULL_ARCH.getArchName());
        assertEquals("Push", ArchitectureEnum.PUSH_ARCH.getArchName());
        assertEquals("Peer to Peer", ArchitectureEnum.P2P_ARCH.getArchName());
    }
}