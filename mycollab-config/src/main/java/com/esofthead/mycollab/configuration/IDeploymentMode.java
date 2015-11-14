package com.esofthead.mycollab.configuration;

/**
 * @author MyCollab Ltd
 * @since 5.2.2
 */
public interface IDeploymentMode {
    boolean isDemandEdition();

    boolean isCommunityEdition();
}
