package com.mycollab.vaadin;

import com.mycollab.configuration.SiteConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServletRequest;

/**
 * @author MyCollab Ltd
 * @since 5.0.10
 */
public class Utils {
    public static String getSubDomain(VaadinRequest request) {
        VaadinServletRequest servletRequest = (VaadinServletRequest) request;
        if (SiteConfiguration.isDemandEdition()) {
            return servletRequest.getServerName().split("\\.")[0];
        } else {
            return servletRequest.getServerName();
        }
    }

    public static boolean isTablet(VaadinRequest request) {
        try {
            String userAgent = request.getHeader("user-agent").toLowerCase();
            return userAgent.contains("ipad");
        } catch (Exception e) {
            return false;
        }
    }
}
