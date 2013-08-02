package com.esofthead.mycollab.module.file.servlet;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.HttpRequestHandler;

import com.esofthead.mycollab.core.MyCollabException;
import com.esofthead.mycollab.module.file.FileStorageConfig;
import com.esofthead.mycollab.module.file.StorageSetting;
import com.esofthead.mycollab.module.file.service.ContentService;

@Component("userAvatarFSServlet")
public class AnnotatedUserAvatarServlet implements HttpRequestHandler {

	private static Logger log = LoggerFactory
			.getLogger(AnnotatedUserAvatarServlet.class);

	@Autowired
	private ContentService contentService;

	@Override
	public void handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		if (!StorageSetting.isFileStorage()) {
			throw new MyCollabException(
					"This servlet support file system setting only");
		}

		String path = request.getPathInfo();
		File avatarFile = null;
		String username = "";
		int size = 0;

		if (path != null) {
			String[] params = path.split("/");
			if (params.length >= 3) {
				username = params[1];
				size = Integer.parseInt(params[2]);
			}
		}

		avatarFile = FileStorageConfig.getAvatarFile(username, size);

		if (avatarFile == null) {
			if (size == 0) {
				throw new MyCollabException("Invalid request for avatar "
						+ path);
			} else {
				String realpath = request.getSession().getServletContext()
						.getRealPath("");
				String userAvatarPath = realpath
						+ "/assets/images/default_user_avatar_" + size + ".png";
				avatarFile = new File(userAvatarPath);
				log.debug("Get default user avatar at "
						+ avatarFile.getAbsolutePath());
			}
		}

		response.setHeader("Content-Type", "image/png");
		response.setHeader("Content-Length",
				String.valueOf(avatarFile.length()));
		response.setHeader("Content-Disposition", "inline; filename=\""
				+ avatarFile.getName() + "\"");

		BufferedInputStream input = null;
		BufferedOutputStream output = null;

		try {
			input = new BufferedInputStream(new FileInputStream(avatarFile));
			output = new BufferedOutputStream(response.getOutputStream());
			byte[] buffer = new byte[8192];
			int length = 0;
			while ((length = input.read(buffer)) > 0) {
				output.write(buffer, 0, length);
			}
		} finally {
			if (output != null)
				try {
					output.close();
				} catch (IOException logOrIgnore) {
				}
			if (input != null)
				try {
					input.close();
				} catch (IOException logOrIgnore) {
				}
		}
	}
}
