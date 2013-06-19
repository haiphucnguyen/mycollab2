package com.esofthead.mycollab.module.file.service.impl;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esofthead.mycollab.core.MyCollabException;
import com.esofthead.mycollab.module.file.service.ContentService;
import com.esofthead.mycollab.module.file.service.UserAvatarService;
import com.esofthead.mycollab.module.user.accountsettings.profile.view.ImageUtil;

@Service
public class UserAvatarServiceImpl implements UserAvatarService {
	@Autowired
	private ContentService contentService;

	private static final int PIXELS_100 = 100;
	private static final int PIXELS_64 = 64;
	private static final int PIXELS_48 = 48;
	private static final int PIXELS_32 = 32;
	private static final int PIXELS_24 = 24;
	private static final int PIXELS_16 = 16;

	private static final int[] SUPPORT_SIZES = { PIXELS_100, PIXELS_64,
			PIXELS_48, PIXELS_32, PIXELS_24, PIXELS_16 };

	@Override
	public void uploadAvatar(BufferedImage image, String username) {
		for (int i = 0; i < SUPPORT_SIZES.length; i++) {
			uploadAvatarToStorage(image, username, SUPPORT_SIZES[i]);
		}
	}

	private void uploadAvatarToStorage(BufferedImage image, String username,
			int width) {
		BufferedImage scaleImage = ImageUtil.scaleImage(image, (float) width
				/ image.getWidth());
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		try {
			ImageIO.write(scaleImage, "png", outStream);
		} catch (IOException e) {
			throw new MyCollabException("Error while write image to stream", e);
		}
		contentService.saveContent(null, "avatar/" + username + "_" + width
				+ ".png", new ByteArrayInputStream(outStream.toByteArray()));
	}
}
