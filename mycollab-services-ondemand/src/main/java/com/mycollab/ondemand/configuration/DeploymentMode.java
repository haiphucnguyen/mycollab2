package com.mycollab.ondemand.configuration;

import com.mycollab.configuration.ApplicationProperties;
import com.mycollab.configuration.IDeploymentMode;
import com.mycollab.configuration.SiteConfiguration;
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

    @Override
    public String getSiteUrl(String subDomain) {
//        return String.format(ApplicationProperties.getString(ApplicationProperties.APP_URL), subDomain);
        return String.format(ApplicationProperties.getString(ApplicationProperties.APP_URL),
                SiteConfiguration.getServerAddress(), SiteConfiguration.getServerPort());
    }
}
