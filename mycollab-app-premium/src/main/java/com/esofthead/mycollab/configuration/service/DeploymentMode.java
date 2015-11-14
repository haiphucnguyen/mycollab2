package com.esofthead.mycollab.configuration.service;

import com.esofthead.mycollab.configuration.IDeploymentMode;

/**
 * @author MyCollab Ltd
 * @since 5.2.2
 */
public class DeploymentMode implements IDeploymentMode {
    @Override
    public boolean isDemandEdition() {
        return false;
    }

    @Override
    public boolean isCommunityEdition() {
        return false;
    }
}
