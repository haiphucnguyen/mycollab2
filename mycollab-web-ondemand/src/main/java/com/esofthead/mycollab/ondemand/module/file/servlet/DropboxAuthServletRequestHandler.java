/**
 * This file is part of mycollab-servlet.
 *
 * mycollab-servlet is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * mycollab-servlet is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with mycollab-servlet.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.esofthead.mycollab.ondemand.module.file.servlet;

import com.dropbox.core.*;
import com.esofthead.mycollab.cache.LocalCacheManager;
import com.esofthead.mycollab.configuration.SiteConfiguration;
import com.esofthead.mycollab.core.MyCollabException;
import com.esofthead.mycollab.module.ecm.StorageNames;
import com.esofthead.mycollab.module.ecm.esb.CloudDriveOAuthCallbackEvent;
import com.esofthead.mycollab.module.file.CloudDriveInfo;
import com.esofthead.mycollab.servlet.GenericHttpServlet;
import com.esofthead.mycollab.web.DesktopApplication;
import com.google.common.eventbus.EventBus;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.UI;
import org.infinispan.commons.api.BasicCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.Locale;

/**
 * @author MyCollab Ltd.
 * @since 1.0
 */
@WebServlet(name = "dropboxAuthServlet", urlPatterns = "/drive/dropboxAuth")
public class DropboxAuthServletRequestHandler extends GenericHttpServlet {
    private static final Logger LOG = LoggerFactory.getLogger(DropboxAuthServletRequestHandler.class);

    private DbxWebAuth getWebAuth(final HttpServletRequest request) {
        Locale locale = new Locale(Locale.US.getLanguage(), Locale.US.getCountry());
        String userLocale = locale.toString();
        DbxRequestConfig requestConfig = new DbxRequestConfig("text-edit/0.1", userLocale);
        DbxAppInfo appInfo = new DbxAppInfo("y43ga49m30dfu02", "rheskqqb6f8fo6a");
        String redirectUri = SiteConfiguration.getDropboxCallbackUrl();
        HttpSession session = request.getSession(true);
        String sessionKey = "dropbox-auth-csrf-token";
        DbxSessionStore csrfTokenStore = new DbxStandardSessionStore(session, sessionKey);
        String stateParam = request.getParameter("state");
        if (stateParam == null || stateParam.equals("")) {
            throw new MyCollabException("Can not get state parameter successfully, Invalid request");
        }

        int index = stateParam.indexOf("|");
        if (index < 0) {
            throw new MyCollabException("Invalid parameter request " + stateParam);
        }

        String oldSessionId = stateParam.substring(index + 1);
        BasicCache<String, Object> cache = LocalCacheManager.getCache(oldSessionId);
        Object csrfTokenVal = cache.get(sessionKey);

        if (csrfTokenVal == null) {
            throw new MyCollabException("Invalid parameter request, can not define csrfToken");
        } else {
            csrfTokenStore.set((String) csrfTokenVal);
        }

        DbxWebAuth webAuth = new DbxWebAuth(requestConfig, appInfo, redirectUri, csrfTokenStore);
        return webAuth;
    }

    @Override
    protected void onHandleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DbxAuthFinish authFinish;
        try {
            authFinish = getWebAuth(request).finish(request.getParameterMap());
        } catch (Exception e) {
            LOG.error("Authorize dropbox request failed", e);
            PrintWriter out = response.getWriter();
            out.println("<html>"
                    + "<body></body>"
                    + "<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js\"></script>"
                    + "<script>");
            out.println("$(document).ready(function(){" + "window.close();"
                    + "});");
            out.println("</script>");
            out.println("</html>");
            return;
        }

        String accessToken = authFinish.accessToken;
        String appId = authFinish.urlState;
        if (appId.startsWith("|")) {
            appId = appId.substring(1);
        }

        // Store accessToken ...
        CloudDriveInfo cloudDriveInfo = new CloudDriveInfo(StorageNames.DROPBOX, accessToken);
        BasicCache<String, Object> cache = LocalCacheManager.getCache(appId);

        Collection<VaadinSession> allSessions = VaadinSession.getAllSessions(request.getSession());
        if (allSessions.size() > 0) {
            for (VaadinSession vaadinSession : allSessions) {
                Collection<UI> uIs = vaadinSession.getUIs();
                if (uIs.size() > 0) {
                    for (UI ui : uIs) {
                        if (ui instanceof DesktopApplication) {
                            LOG.debug("Desktop");
                        }
                    }
                }
            }
        }

        EventBus eventBus = (EventBus) cache.get("eventBusVal");
        if (eventBus != null) {
            eventBus.post(new CloudDriveOAuthCallbackEvent.ReceiveCloudDriveInfo(
                    DropboxAuthServletRequestHandler.this, cloudDriveInfo));
        } else {
//            LOG.error("Can not find eventbus for session id {}, this session is not initialized by user yet", appId);
        }

        // response script close current window
        PrintWriter out = response.getWriter();
        out.println("<html>"
                + "<body></body>"
                + "<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js\"></script>"
                + "<script>");
        out.println("$(document).ready(function(){" + "window.close();" + "});");
        out.println("</script>");
        out.println("</html>");
    }
}
