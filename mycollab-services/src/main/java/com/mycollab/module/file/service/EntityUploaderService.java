package com.mycollab.module.file.service;

import java.awt.image.BufferedImage;

/**
 * @author MyCollab Ltd
 * @since 5.2.12
 */
public interface EntityUploaderService {
    /**
     * @param logo
     * @param basePath
     * @param oldId
     * @param uploadedUser
     * @param sAccountId
     * @param preferSizes
     * @return
     */
    String upload(BufferedImage logo, String basePath, String oldId, String uploadedUser,
                  Integer sAccountId, Integer[] preferSizes);
}
