/**
 * This file is part of mycollab-services.
 *
 * mycollab-services is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * mycollab-services is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with mycollab-services.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.mycollab.module.file.service;

import com.mycollab.configuration.ServerConfiguration;
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
        return serverConfiguration.getDownloadUrl() + documentPath;
    }

    public String getLogoPath(Integer accountId, String logoName, Integer size) {
        if (StringUtils.isBlank(logoName)) {
            return generateAssetRelativeLink("icons/logo.png");
        }
        return String.format("%s%d/.assets/%s_%d.png", serverConfiguration.getDownloadUrl(), accountId,
                logoName, size);
    }

    public String getEntityLogoPath(Integer accountId, String id, Integer size) {
        return String.format("%s%d/.assets/%s_%d.png", serverConfiguration.getDownloadUrl(), accountId,
                id, size);
    }

    public String getFavIconPath(Integer sAccountId, String favIconName) {
        if (StringUtils.isBlank(favIconName)) {
            return generateAssetRelativeLink("favicon.ico");
        }
        return String.format("%s%d/.assets/%s.ico", serverConfiguration.getDownloadUrl(), sAccountId, favIconName);
    }

    public String getAvatarPath(String userAvatarId, Integer size) {
        if (StringUtils.isBlank(userAvatarId)) {
            return generateAssetRelativeLink(String.format("icons/default_user_avatar_%d.png", size));
        } else {
            return String.format("%savatar/%s_%d.png", serverConfiguration.getDownloadUrl(), userAvatarId, size);
        }
    }

    public abstract String generateAssetRelativeLink(String resourceId);
}
