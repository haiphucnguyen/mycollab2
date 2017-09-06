package com.mycollab.community.configuration;

import com.mycollab.configuration.ApplicationProperties;
import com.mycollab.configuration.IDeploymentMode;
import com.mycollab.configuration.ServerConfiguration;
import com.mycollab.configuration.SiteConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author MyCollab Ltd
 * @since 5.2.2
 */
@Component
@Profile("production")
@Order(value = 1)
public class DeploymentMode implements IDeploymentMode {

    @Autowired
    private ServerConfiguration serverConfiguration;

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
        return String.format(ApplicationProperties.getString(ApplicationProperties.APP_URL),
                SiteConfiguration.getServerAddress(), serverConfiguration.getPort());
    }
}
