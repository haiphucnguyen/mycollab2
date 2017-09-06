package com.mycollab.configuration;

/**
 * @author MyCollab Ltd
 * @since 5.2.2
 */
public interface IDeploymentMode {
    boolean isDemandEdition();

    boolean isCommunityEdition();

    boolean isPremiumEdition();

    String getSiteUrl(String subDomain);
}
