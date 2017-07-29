package com.mycollab.module.file.service.impl;

import com.mycollab.core.MyCollabException;
import com.mycollab.core.utils.ImageUtil;
import com.mycollab.core.utils.StringUtils;
import com.mycollab.module.ecm.domain.Content;
import com.mycollab.module.ecm.service.ResourceService;
import com.mycollab.module.file.service.EntityUploaderService;
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
    public String upload(BufferedImage image, String basePath, String oldId, String uploadedUser,
                         Integer sAccountId, Integer[] preferSizes) {
        // Construct new logoid
        String newLogoId = new GregorianCalendar().getTimeInMillis() + UUID.randomUUID().toString();

        for (int preferSize : preferSizes) {
            uploadLogoToStorage(uploadedUser, image, basePath, newLogoId, preferSize);
        }

        if (StringUtils.isNotBlank(oldId)) {
            for (Integer preferSize : preferSizes) {
                try {
                    resourceService.removeResource(String.format("%s/%s_%d.png", basePath, oldId, preferSize),
                            uploadedUser, true, sAccountId);
                } catch (Exception e) {
                    LOG.error("Error while delete old logo", e);
                }
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
