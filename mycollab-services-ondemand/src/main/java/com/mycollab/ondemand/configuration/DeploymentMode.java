package com.mycollab.ondemand.configuration;

import com.mycollab.configuration.IDeploymentMode;
import com.mycollab.configuration.ServerConfiguration;
import com.mycollab.configuration.SiteConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

/**
 * @author MyCollab Ltd
 * @since 5.2.2
 */
@Order(value = 1)
@Service
public class DeploymentMode implements IDeploymentMode {

    @Autowired
    private ServerConfiguration serverConfiguration;

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

    @Override
    public String getSiteUrl(String subDomain) {
        return String.format(serverConfiguration.getAppUrl(), subDomain);
    }
}
