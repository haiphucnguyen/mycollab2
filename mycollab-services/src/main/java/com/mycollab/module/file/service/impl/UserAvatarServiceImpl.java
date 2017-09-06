package com.mycollab.module.file.service.impl;

import com.mycollab.core.MyCollabException;
import com.mycollab.core.utils.StringUtils;
import com.mycollab.module.ecm.service.ResourceService;
import com.mycollab.module.file.service.EntityUploaderService;
import com.mycollab.module.file.service.UserAvatarService;
import com.mycollab.module.user.dao.UserMapper;
import com.mycollab.module.user.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author MyCollab Ltd.
 * @since 1.0
 */
@Service(value = "userAvatarService")
public class UserAvatarServiceImpl implements UserAvatarService {

    @Autowired
    private ResourceService resourceService;

    @Autowired
    private EntityUploaderService entityUploaderService;

    @Autowired
    private UserMapper userMapper;

    private static final Integer[] SUPPORT_SIZES = {100, 64, 48, 32, 24, 16};

    @Override
    public String uploadDefaultAvatar(String username) {
        // Save default user avatar
        InputStream imageResourceStream = this.getClass().getClassLoader().getResourceAsStream("assets/icons/default_user_avatar_100.png");
        BufferedImage imageBuff;
        try {
            imageBuff = ImageIO.read(imageResourceStream);
            return uploadAvatar(imageBuff, username, null);
        } catch (IOException e) {
            throw new MyCollabException("Error while set default avatar to user", e);
        }
    }

    @Override
    public String uploadAvatar(BufferedImage image, String username, String avatarId) {
        String newAvatarId = entityUploaderService.upload(image, "avatar", avatarId, username, null, SUPPORT_SIZES);

        // save avatar id
        User user = new User();
        user.setUsername(username);
        user.setAvatarid(newAvatarId);
        userMapper.updateByPrimaryKeySelective(user);
        return newAvatarId;
    }

    @Override
    public void removeAvatar(String avatarId) {
        if (StringUtils.isNotBlank(avatarId)) {
            for (int size : SUPPORT_SIZES) {
                resourceService.removeResource(String.format("%s/%s_%d.png", "avatar", avatarId, size));
            }
        }
    }
}
