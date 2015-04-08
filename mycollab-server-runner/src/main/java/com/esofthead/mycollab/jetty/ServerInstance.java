package com.esofthead.mycollab.jetty;

import com.esofthead.mycollab.core.MyCollabException;

import java.io.File;

/**
 * @author MyCollab Ltd.
 * @since 5.0.4
 */
public class ServerInstance {
    private static ServerInstance instance = new ServerInstance();
    private GenericServerRunner server;

    private boolean isFirstTimeRunner = false;
    private boolean isUpgrading = false;

    private ServerInstance() {}

    public void registerInstance(GenericServerRunner serverProcess) {
        if (server != null) {
            throw new MyCollabException("There is a running server instance already");
        }
        this.server = serverProcess;
    }

    public void upgrade(File upgradeFile) {
        isUpgrading = true;
        server.upgrade(upgradeFile);
    }

    public boolean isUpgrading() {
        return isUpgrading;
    }

    void setIsUpgrading(boolean isUpgrading) {
        this.isUpgrading = isUpgrading;
    }

    public static ServerInstance getInstance() {
        return instance;
    }

    public boolean isFirstTimeRunner() {
        return isFirstTimeRunner;
    }

    public void setIsFirstTimeRunner(boolean isFirstTimeRunner) {
        this.isFirstTimeRunner = isFirstTimeRunner;
    }
}
