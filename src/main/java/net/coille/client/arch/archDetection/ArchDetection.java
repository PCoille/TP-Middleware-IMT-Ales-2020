package net.coille.client.arch.archDetection;

import net.coille.client.ui.UIController;
import net.coille.common.arch.ArchitectureEnum;

public interface ArchDetection {
    ArchitectureEnum getServerArchitecture();
    UIController getUIControllerClient();
}
