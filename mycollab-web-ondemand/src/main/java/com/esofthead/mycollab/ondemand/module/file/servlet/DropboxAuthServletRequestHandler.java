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

import com.esofthead.mycollab.cache.LocalCacheManager;
import com.esofthead.mycollab.module.ecm.StorageNames;
import com.esofthead.mycollab.module.ecm.esb.CloudDriveOAuthCallbackEvent;
import com.esofthead.mycollab.module.file.CloudDriveInfo;
import com.esofthead.mycollab.oauth.service.MyCollabOauthServiceFactory;
import com.esofthead.mycollab.servlet.GenericHttpServlet;
import com.esofthead.mycollab.vaadin.MyCollabUI;
import com.esofthead.mycollab.vaadin.ui.MyCollabSession;
import com.google.common.eventbus.EventBus;
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

        try {
            // getting access token
            Verifier verifier = new Verifier(oauthVerifier);
            OAuthService dropboxService = MyCollabOauthServiceFactory.getDropboxService();
            Token accessToken = dropboxService.getAccessToken(null, verifier);
            final String accessTokenVal = accessToken.getToken();
            CloudDriveInfo cloudDriveInfo = new CloudDriveInfo(StorageNames.DROPBOX, accessTokenVal);

            UI ui = (UI) LocalCacheManager.getCache("tempCache").get(state);
            if (ui instanceof MyCollabUI) {
                MyCollabUI myUI = (MyCollabUI) ui;
                EventBus eventBus = (EventBus) myUI.getAttribute(MyCollabSession.EVENT_BUS_VAL);
                if (eventBus != null) {
                    eventBus.post(new CloudDriveOAuthCallbackEvent.ReceiveCloudDriveInfo(
                            DropboxAuthServletRequestHandler.this, cloudDriveInfo));
                }
            }
        } finally {
            LocalCacheManager.getCache("tempCache").removeAsync(state);
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
