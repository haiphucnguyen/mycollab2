package com.esofthead.mycollab.module.file.service;

import java.awt.image.BufferedImage;

public interface UserAvatarService {
	void uploadAvatar(BufferedImage image, String username, int sAccountId);
}
