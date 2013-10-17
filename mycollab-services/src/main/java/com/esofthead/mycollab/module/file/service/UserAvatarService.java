package com.esofthead.mycollab.module.file.service;

import java.awt.image.BufferedImage;

import com.esofthead.mycollab.core.dist.NotMobile;
import com.esofthead.mycollab.core.persistence.service.IService;

public interface UserAvatarService extends IService {
	@NotMobile
	String uploadAvatar(BufferedImage image, String username, String avatarId);
}
