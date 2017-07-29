package com.mycollab.module.file.service;

import com.mycollab.db.persistence.service.IService;

import java.awt.image.BufferedImage;

/**
 * @author MyCollab Ltd.
 * @since 4.1.2
 */
public interface AccountLogoService extends IService {
    String upload(String uploadedUser, BufferedImage logo, Integer sAccountId);
}
