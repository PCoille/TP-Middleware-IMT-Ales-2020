package net.coille.middleware.client.arch.archDetection;

import net.coille.middleware.client.ui.UIController;
import net.coille.middleware.common.arch.ArchitectureEnum;

public interface ArchDetection {
    ArchitectureEnum getServerArchitecture();
    UIController getUIControllerClient();
}
