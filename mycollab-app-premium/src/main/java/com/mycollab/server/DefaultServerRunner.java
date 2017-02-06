package com.mycollab.server;

import org.eclipse.jetty.server.handler.gzip.GzipHandler;
import org.eclipse.jetty.webapp.WebAppContext;

/**
 * @author MyCollab Ltd.
 * @since 1.0
 */
public class DefaultServerRunner extends JettyServerBasedRunner {

    @Override
    public WebAppContext buildContext(String baseDir) {
        WebAppContext webAppContext = new WebAppContext();
        webAppContext.setContextPath("/");
        webAppContext.setWar(baseDir);
        webAppContext.setResourceBase(baseDir);
        GzipHandler gzipHandler = new GzipHandler();
        gzipHandler.addIncludedMimeTypes("text/html,text/plain,text/xml,application/xhtml+xml,text/css," +
                "application/javascript, image/svg+xml");
        webAppContext.setGzipHandler(gzipHandler);
        return webAppContext;
    }

    public static void main(String[] args) throws Exception {
        new DefaultServerRunner().run(args);
    }

}
