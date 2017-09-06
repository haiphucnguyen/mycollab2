package com.mycollab.vaadin;

import com.mycollab.core.Version;
import com.vaadin.addon.touchkit.annotations.CacheManifestEnabled;
import com.vaadin.addon.touchkit.server.TouchKitServlet;
import com.vaadin.addon.touchkit.settings.TouchKitSettings;
import com.vaadin.server.DeploymentConfiguration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import java.util.Properties;

/**
 * @author MyCollab Ltd.
 * @since 3.0
 */
@WebServlet(name = "MyCollabApplication", urlPatterns = "/*", asyncSupported = true, loadOnStartup = 0, initParams =
        {@WebInitParam(name = "closeIdleSessions", value = "false"),
                @WebInitParam(name = "productionMode", value = "true"),
                @WebInitParam(name = "resourceCacheTime", value = "8640000"),
                @WebInitParam(name = "maxIdleTime", value = "10000"),
                @WebInitParam(name = "org.atmosphere.websocket.maxIdleTime", value = "86400000")
        })
@CacheManifestEnabled(false)
public class AppServlet extends TouchKitServlet {
    private static final long serialVersionUID = 1L;

    private AppUIProvider uiProvider = new AppUIProvider();
    private AppBootstrapListener bootstrapListener = new AppBootstrapListener();

    @Override
    protected DeploymentConfiguration createDeploymentConfiguration(Properties initParameters) {
        initParameters.setProperty("productionMode", "true");
        return super.createDeploymentConfiguration(initParameters);
    }

    @Override
    protected void servletInitialized() throws ServletException {
        super.servletInitialized();
        TouchKitSettings s = getTouchKitSettings();
        s.getWebAppSettings().setWebAppCapable(true);
        String contextPath = getServletConfig().getServletContext().getContextPath();
        s.getApplicationIcons().addApplicationIcon(contextPath + "VAADIN/themes/" + Version.THEME_MOBILE_VERSION + "/icons/icon.png");
        s.getWebAppSettings().setStartupImage(contextPath + "VAADIN/themes/" + Version.THEME_MOBILE_VERSION + "/icons/icon.png");

        getService().addSessionInitListener(sessionInitEvent -> {
            sessionInitEvent.getSession().addBootstrapListener(bootstrapListener);
            sessionInitEvent.getSession().addUIProvider(uiProvider);
        });
    }
}
