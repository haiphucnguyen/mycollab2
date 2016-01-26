package com.esofthead.mycollab.configuration.service;

import com.esofthead.mycollab.configuration.IDeploymentMode;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

/**
 * @author MyCollab Ltd
 * @since 5.2.2
 */
@Order(value=1)
@Service
public class DeploymentMode implements IDeploymentMode {
    @Override
    public boolean isDemandEdition() {
        return true;
    }

    @Override
    public boolean isCommunityEdition() {
        return false;
    }

    @Override
    public boolean isPremiumEdition() {
        return false;
    }
}
