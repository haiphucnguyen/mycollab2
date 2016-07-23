package com.mycollab.ondemand.module.file.servlet;

import com.google.common.eventbus.EventBus;
import com.mycollab.cache.service.CacheService;
import com.mycollab.module.ecm.StorageNames;
import com.mycollab.module.file.CloudDriveInfo;
import com.mycollab.oauth.service.MyCollabOauthServiceFactory;
import com.mycollab.ondemand.module.file.event.CloudDriveOAuthCallbackEvent;
import com.mycollab.servlet.GenericHttpServlet;
import com.mycollab.spring.AppContextUtil;
import com.mycollab.vaadin.MyCollabUI;
import com.mycollab.vaadin.ui.MyCollabSession;
import com.vaadin.ui.UI;
import org.scribe.model.Token;
import org.scribe.model.Verifier;
import org.scribe.oauth.OAuthService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author MyCollab Ltd.
 * @since 1.0
 */
@WebServlet(name = "dropboxAuthServlet", urlPatterns = "/drive/dropboxAuth")
public class DropboxAuthServletRequestHandler extends GenericHttpServlet {
    @Override
    protected void onHandleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String oauthVerifier = request.getParameter("code");
        String state = request.getParameter("state");
        CacheService cacheService = AppContextUtil.getSpringBean(CacheService.class);

        try {
            // getting access token
            Verifier verifier = new Verifier(oauthVerifier);
            OAuthService dropboxService = MyCollabOauthServiceFactory.getDropboxService();
            Token accessToken = dropboxService.getAccessToken(null, verifier);
            final String accessTokenVal = accessToken.getToken();
            CloudDriveInfo cloudDriveInfo = new CloudDriveInfo(StorageNames.DROPBOX, accessTokenVal);

            UI ui = (UI) cacheService.getValue("tempCache", state);
            if (ui instanceof MyCollabUI) {
                MyCollabUI myUI = (MyCollabUI) ui;
                EventBus eventBus = (EventBus) myUI.getAttribute(MyCollabSession.EVENT_BUS_VAL);
                if (eventBus != null) {
                    eventBus.post(new CloudDriveOAuthCallbackEvent.ReceiveCloudDriveInfo(
                            DropboxAuthServletRequestHandler.this, cloudDriveInfo));
                }
            }
        } finally {
            cacheService.removeCacheItem("tempCache", state);
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
}
