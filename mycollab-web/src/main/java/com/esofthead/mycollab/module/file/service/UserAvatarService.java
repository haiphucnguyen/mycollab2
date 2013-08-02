package com.esofthead.mycollab.module.file.service;

import java.awt.image.BufferedImage;

public interface UserAvatarService {
	String uploadAvatar(BufferedImage image, String username, String avatarId);
}
