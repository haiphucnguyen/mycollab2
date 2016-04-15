package com.esofthead.mycollab.module.file.service.impl;

import com.esofthead.mycollab.core.MyCollabException;
import com.esofthead.mycollab.core.utils.ImageUtil;
import com.esofthead.mycollab.module.ecm.domain.Content;
import com.esofthead.mycollab.module.ecm.service.ResourceService;
import com.esofthead.mycollab.module.file.service.EntityUploaderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.GregorianCalendar;
import java.util.UUID;

/**
 * @author MyCollab Ltd
 * @since 5.2.12
 */
@Service
public class EntityUploaderServiceImpl implements EntityUploaderService {
    private static final Logger LOG = LoggerFactory.getLogger(EntityUploaderServiceImpl.class);

    @Autowired
    private ResourceService resourceService;

    @Override
    public String upload(BufferedImage image, String basePath, String oldId, String uploadedUser, Integer sAccountId, int[] preferSizes) {
        // Construct new logoid
        String newLogoId = new GregorianCalendar().getTimeInMillis() + UUID.randomUUID().toString();

        for (int i = 0; i < preferSizes.length; i++) {
            uploadLogoToStorage(uploadedUser, image, basePath, newLogoId, preferSizes[i]);
        }

        // account old logo
        for (int i = 0; i < preferSizes.length; i++) {
            try {
                resourceService.removeResource(String.format("%s/%s_%d.png", basePath, oldId, preferSizes[i]),
                        uploadedUser, sAccountId);
            } catch (Exception e) {
                LOG.error("Error while delete old logo", e);
            }
        }
        return newLogoId;
    }

    private void uploadLogoToStorage(String uploadedUser, BufferedImage image, String basePath, String logoId, int width) {
        BufferedImage scaleImage = ImageUtil.scaleImage(image, (float) width / image.getWidth());
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        try {
            ImageIO.write(scaleImage, "png", outStream);
        } catch (IOException e) {
            throw new MyCollabException("Error while write image to stream", e);
        }
        Content logoContent = new Content();
        logoContent.setPath(String.format("%s/%s_%d.png", basePath, logoId, width));
        logoContent.setName(logoId + "_" + width);
        resourceService.saveContent(logoContent, uploadedUser, new ByteArrayInputStream(outStream.toByteArray()), null);
    }
}
