package com.mycollab.server;

import org.eclipse.jetty.webapp.WebAppContext;

/**
 * @author MyCollab Ltd
 * @since 5.4.1
 */
public class CommunityJettyBaseRunner extends JettyServerBasedRunner {
    @Override
    public WebAppContext buildContext(String baseDir) {
        WebAppContext webAppContext = new WebAppContext();
        webAppContext.setContextPath("/");
        webAppContext.setWar(baseDir);
        webAppContext.setResourceBase(baseDir);
//        GzipHandler gzipHandler = new GzipHandler();
//        gzipHandler.addIncludedMimeTypes("text/html,text/plain,text/xml,application/xhtml+xml,text/css,application/javascript,image/svg+xml");
//        webAppContext.setGzipHandler(gzipHandler);
        return webAppContext;
    }
}
