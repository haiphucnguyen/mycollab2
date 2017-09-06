package com.mycollab.test.spring;

import com.mycollab.configuration.IDeploymentMode;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author MyCollab Ltd
 * @since 5.2.2
 */
@Component
@Profile("test")
@Order(value = 10)
public class DeploymentMode implements IDeploymentMode {
    @Override
    public boolean isDemandEdition() {
        return false;
    }

    @Override
    public boolean isCommunityEdition() {
        return true;
    }

    @Override
    public boolean isPremiumEdition() {
        return false;
    }

    @Override
    public String getSiteUrl(String subDomain) {
        return "";
    }
}
