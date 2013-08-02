package com.esofthead.mycollab.module.file.service.impl;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esofthead.mycollab.core.MyCollabException;
import com.esofthead.mycollab.module.file.service.ContentService;
import com.esofthead.mycollab.module.file.service.UserAvatarService;
import com.esofthead.mycollab.module.user.accountsettings.profile.view.ImageUtil;
import com.esofthead.mycollab.module.user.dao.UserMapper;
import com.esofthead.mycollab.module.user.domain.User;

@Service
public class UserAvatarServiceImpl implements UserAvatarService {
	private static Logger log = LoggerFactory
			.getLogger(UserAvatarServiceImpl.class);

	@Autowired
	private ContentService contentService;

	@Autowired
	private UserMapper userMapper;

	private static final int PIXELS_100 = 100;
	private static final int PIXELS_64 = 64;
	private static final int PIXELS_48 = 48;
	private static final int PIXELS_32 = 32;
	private static final int PIXELS_24 = 24;
	private static final int PIXELS_16 = 16;

	private static final int[] SUPPORT_SIZES = { PIXELS_100, PIXELS_64,
			PIXELS_48, PIXELS_32, PIXELS_24, PIXELS_16 };

	@Override
	public String uploadAvatar(BufferedImage image, String username,
			String avatarId) {

		// Construct new avatarid
		String randomString = UUID.randomUUID().toString();
		String newAvatarId = username + "_" + randomString;

		for (int i = 0; i < SUPPORT_SIZES.length; i++) {
			uploadAvatarToStorage(image, newAvatarId, SUPPORT_SIZES[i]);
		}

		// save avatar id
		User user = new User();
		user.setUsername(username);
		user.setAvatarid(newAvatarId);
		userMapper.updateByPrimaryKeySelective(user);

		// Delete old avatar
		if (avatarId != null) {
			for (int i = 0; i < SUPPORT_SIZES.length; i++) {
				try {
					contentService.removeContent(null, "avatar/" + avatarId
							+ "_" + SUPPORT_SIZES[i] + ".png");
				} catch (Exception e) {
					log.error("Error while delete old avatar", e);
				}
			}
		}

		return newAvatarId;
	}

	private void uploadAvatarToStorage(BufferedImage image, String avatarId,
			int width) {
		BufferedImage scaleImage = ImageUtil.scaleImage(image, (float) width
				/ image.getWidth());
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		try {
			ImageIO.write(scaleImage, "png", outStream);
		} catch (IOException e) {
			throw new MyCollabException("Error while write image to stream", e);
		}
		contentService.saveContent(null, "avatar/" + avatarId + "_" + width
				+ ".png", new ByteArrayInputStream(outStream.toByteArray()));
	}
}
