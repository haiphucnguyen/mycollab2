/**
 * Copyright © MyCollab
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.mycollab.module.file.servlet;

import com.mycollab.configuration.SiteConfiguration;
import com.mycollab.core.MyCollabException;
import com.mycollab.core.ResourceNotFoundException;
import com.mycollab.core.utils.FileUtils;
import com.mycollab.servlet.GenericHttpServlet;
import org.apache.commons.io.FilenameUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * @author MyCollab Ltd.
 * @since 1.0
 */
@WebServlet(urlPatterns = "/file/avatar/*", name = "userAvatarFSServlet")
public class UserAvatarHttpServletRequestHandler extends GenericHttpServlet {

    @Override
    protected void onHandleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (SiteConfiguration.isDemandEdition()) {
            throw new MyCollabException("This servlet support file system setting only");
        }

        String path = request.getPathInfo();

        if (path != null) {
            path = FilenameUtils.getBaseName(path);
            int lastIndex = path.lastIndexOf("_");
            if (lastIndex > 0) {
                String username = path.substring(0, lastIndex);
                int size = Integer.valueOf(path.substring(lastIndex + 1, path.length()));

                File userAvatarFile = new File(FileUtils.getHomeFolder(), String.format("/avatar/%s_%d.png", username, size));
                InputStream avatarInputStream = new FileInputStream(userAvatarFile);

                response.setHeader("Content-Type", "image/png");
                response.setHeader("Content-Length", String.valueOf(avatarInputStream.available()));

                try (BufferedInputStream input = new BufferedInputStream(avatarInputStream);
                     BufferedOutputStream output = new BufferedOutputStream(response.getOutputStream())) {
                    byte[] buffer = new byte[8192];
                    int length;
                    while ((length = input.read(buffer)) > 0) {
                        output.write(buffer, 0, length);
                    }
                }
            } else {
                throw new ResourceNotFoundException("Invalid path " + path);
            }
        }
    }
}
