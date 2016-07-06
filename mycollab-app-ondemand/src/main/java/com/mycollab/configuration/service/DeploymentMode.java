package com.mycollab.configuration.service;

import com.mycollab.configuration.IDeploymentMode;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

/**
 * @author MyCollab Ltd
 * @since 5.2.2
 */
@Order(value = 1)
@Service
public class DeploymentMode implements IDeploymentMode {
    @Override
    public boolean isDemandEdition() {
        return false;
    }

    @Override
    public boolean isCommunityEdition() {
        return false;
    }

    @Override
    public boolean isPremiumEdition() {
        return true;
    }
}
