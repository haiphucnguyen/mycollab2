package com.mycollab.premium.configuration;

import com.mycollab.configuration.ApplicationProperties;
import com.mycollab.configuration.IDeploymentMode;
import com.mycollab.configuration.ServerConfiguration;
import com.mycollab.configuration.SiteConfiguration;
import com.mycollab.db.persistence.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

/**
 * @author MyCollab Ltd
 * @since 5.2.2
 */
@Service
@Order(value = 1)
public class DeploymentMode implements IDeploymentMode, IService {

    @Autowired
    private ServerConfiguration serverConfiguration;

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
        return String.format(ApplicationProperties.getString(ApplicationProperties.APP_URL),
                SiteConfiguration.getServerAddress(), serverConfiguration.getPort());
    }
}
