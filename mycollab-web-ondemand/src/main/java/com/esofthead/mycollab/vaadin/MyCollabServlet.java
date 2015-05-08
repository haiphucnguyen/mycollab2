package com.esofthead.mycollab.vaadin;

import com.vaadin.addon.touchkit.server.TouchKitServlet;
import com.vaadin.addon.touchkit.settings.TouchKitSettings;
import com.vaadin.server.SessionInitEvent;
import com.vaadin.server.SessionInitListener;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;

/**
 * @author MyCollab Ltd.
 * @since 3.0
 */
@WebServlet(name = "MyCollabApplication", urlPatterns = "/*", asyncSupported = false, loadOnStartup = 0, initParams =
        {@WebInitParam(name = "closeIdleSessions", value = "true")})
public class MyCollabServlet extends TouchKitServlet {
    private static final long serialVersionUID = 1L;

    private MyCollabUIProvider uiProvider = new MyCollabUIProvider();

    @Override
    protected void servletInitialized() throws ServletException {
        super.servletInitialized();
        TouchKitSettings s = getTouchKitSettings();
        s.getApplicationCacheSettings().setCacheManifestEnabled(false);

        getService().addSessionInitListener(new SessionInitListener() {
            private static final long serialVersionUID = 1L;

            @Override
            public void sessionInit(SessionInitEvent event) {
                event.getSession().addBootstrapListener(new MyCollabBootstrapListener());
                event.getSession().addUIProvider(uiProvider);
            }
        });
    }
}
