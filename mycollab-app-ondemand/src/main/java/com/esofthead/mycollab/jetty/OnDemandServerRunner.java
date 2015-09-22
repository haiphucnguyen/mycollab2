package com.esofthead.mycollab.jetty;

import org.eclipse.jetty.webapp.WebAppContext;

/**
 * @author MyCollab Ltd.
 * @since 1.0
 */
public class OnDemandServerRunner extends GenericServerRunner {

    @Override
    public WebAppContext buildContext(String baseDir) {
        WebAppContext webAppContext = new WebAppContext();
        webAppContext.setContextPath("/");
        webAppContext.setWar(baseDir);
        webAppContext.setResourceBase(baseDir);
        return webAppContext;
    }

    public static void main(String[] args) throws Exception {
        new OnDemandServerRunner().run(args);
    }
}
