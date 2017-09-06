package com.mycollab.module.file.service;

import com.mycollab.db.persistence.service.IService;

import java.awt.image.BufferedImage;

/**
 * @author MyCollab Ltd
 * @since 5.0.10
 */
public interface AccountFavIconService extends IService {
    String upload(String uploadedUser, BufferedImage logo, Integer sAccountId);
}
