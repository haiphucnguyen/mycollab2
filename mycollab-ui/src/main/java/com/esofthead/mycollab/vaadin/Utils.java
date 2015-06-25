package com.esofthead.mycollab.vaadin;

import com.esofthead.mycollab.configuration.SiteConfiguration;
import com.esofthead.mycollab.core.DeploymentMode;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServletRequest;

/**
 * @author MyCollab Ltd
 * @since 5.0.10
 */
public class Utils {
    public static String getSubDomain(VaadinRequest request) {
        VaadinServletRequest servletRequest = (VaadinServletRequest) request;
        if (SiteConfiguration.getDeploymentMode() == DeploymentMode.site) {
            return servletRequest.getServerName().split("\\.")[0];
        } else {
            return servletRequest.getServerName();
        }
    }
}
