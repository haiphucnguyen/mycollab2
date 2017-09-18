package com.mycollab.module.file.service

import java.awt.image.BufferedImage

/**
 * @author MyCollab Ltd
 * @since 5.2.12
 */
interface EntityUploaderService {
    /**
     * @param logo
     * @param basePath
     * @param oldId
     * @param uploadedUser
     * @param sAccountId
     * @param preferSizes
     * @return
     */
    fun upload(logo: BufferedImage, basePath: String, oldId: String, uploadedUser: String,
               sAccountId: Int?, preferSizes: Array<Int>): String
}
