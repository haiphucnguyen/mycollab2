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

import com.esofthead.mycollab.core.MyCollabException;
import com.esofthead.mycollab.module.ecm.StorageNames;
import com.esofthead.mycollab.module.file.CloudDriveInfo;
import com.esofthead.mycollab.oauth.service.MyCollabOauthServiceFactory;
import com.esofthead.mycollab.web.DesktopApplication;
import com.vaadin.server.VaadinServlet;
import com.vaadin.server.VaadinSession;
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
import java.util.Collection;

/**
 * @author MyCollab Ltd.
 * @since 1.0
 */
@WebServlet(name = "dropboxAuthServlet", urlPatterns = "/drive/dropboxAuth")
public class DropboxAuthServletRequestHandler extends VaadinServlet {

    @Override
    protected void service(final HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        String oauthVerifier = request.getParameter("code");
        // getting access token
        Verifier verifier = new Verifier(oauthVerifier);
        OAuthService dropboxService = MyCollabOauthServiceFactory.getDropboxService();
        Token accessToken = dropboxService.getAccessToken(null, verifier);
        final String accessTkenVal = accessToken.getToken();
        try {
            final VaadinSession vaadinSession;
            if (VaadinSession.getCurrent() == null) {
                vaadinSession = getService().findVaadinSession(createVaadinRequest(request));
            } else {
                vaadinSession = VaadinSession.getCurrent();
            }

            vaadinSession.access(new Runnable() {
                @Override
                public void run() {
                    CloudDriveInfo cloudDriveInfo = new CloudDriveInfo(StorageNames.DROPBOX, accessTkenVal);
                    Collection<VaadinSession> allSessions = VaadinSession.getAllSessions(request.getSession());
                    if (allSessions.size() > 0) {
                        for (VaadinSession vaadinSession : allSessions) {
                            Collection<UI> uIs = vaadinSession.getUIs();
                            if (uIs.size() > 0) {
                                for (UI ui : uIs) {
                                    if (ui instanceof DesktopApplication) {
                                        System.out.println("Desktop");
                                    }
                                }
                            }
                        }
                    }

                }
            });
        } catch (Exception e) {
            throw new MyCollabException(e);
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
