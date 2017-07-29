package com.mycollab.module.file.service;

import com.mycollab.configuration.ServerConfiguration;
import com.mycollab.configuration.SiteConfiguration;
import com.mycollab.core.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author MyCollab Ltd
 * @since 5.5.0
 */
public abstract class AbstractStorageService {

    @Autowired
    protected ServerConfiguration serverConfiguration;

    public String getResourcePath(String documentPath) {
        return SiteConfiguration.getResourceDownloadUrl() + documentPath;
    }

    public String getLogoPath(Integer accountId, String logoName, Integer size) {
        if (StringUtils.isBlank(logoName)) {
            return generateAssetRelativeLink("icons/logo.png");
        }
        return String.format("%s%d/.assets/%s_%d.png", SiteConfiguration.getResourceDownloadUrl(), accountId,
                logoName, size);
    }

    public String getEntityLogoPath(Integer accountId, String id, Integer size) {
        return String.format("%s%d/.assets/%s_%d.png", SiteConfiguration.getResourceDownloadUrl(), accountId,
                id, size);
    }

    public String getFavIconPath(Integer sAccountId, String favIconName) {
        if (StringUtils.isBlank(favIconName)) {
            return generateAssetRelativeLink("favicon.ico");
        }
        return String.format("%s%d/.assets/%s.ico", SiteConfiguration.getResourceDownloadUrl(), sAccountId, favIconName);
    }

    public String getAvatarPath(String userAvatarId, Integer size) {
        if (StringUtils.isBlank(userAvatarId)) {
            return generateAssetRelativeLink(String.format("icons/default_user_avatar_%d.png", size));
        } else {
            return String.format("%savatar/%s_%d.png", SiteConfiguration.getResourceDownloadUrl(), userAvatarId, size);
        }
    }

    public abstract String generateAssetRelativeLink(String resourceId);
}
