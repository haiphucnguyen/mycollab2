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
package com.mycollab.module.file;

import com.mycollab.core.utils.StringUtils;
import com.mycollab.module.file.service.AbstractStorageService;
import com.mycollab.spring.AppContextUtil;

/**
 * @author MyCollab Ltd
 * @since 5.5.0
 */
public class StorageUtils {
    public static String getResourcePath(String documentPath) {
        return AppContextUtil.getSpringBean(AbstractStorageService.class).getResourcePath(documentPath);
    }

    public static String getLogoPath(Integer accountId, String logoName, Integer size) {
        return AppContextUtil.getSpringBean(AbstractStorageService.class).getLogoPath(accountId, logoName, size);
    }

    public static String getEntityLogoPath(Integer accountId, String id, Integer size) {
        return AppContextUtil.getSpringBean(AbstractStorageService.class).getEntityLogoPath(accountId, id, size);
    }

    public static String getFavIconPath(Integer sAccountId, String favIconName) {
        return AppContextUtil.getSpringBean(AbstractStorageService.class).getFavIconPath(sAccountId, favIconName);
    }

    public static String getAvatarPath(String userAvatarId, Integer size) {
        return AppContextUtil.getSpringBean(AbstractStorageService.class).getAvatarPath(userAvatarId, size);
    }

    public static String generateAssetRelativeLink(String resourceId) {
        return AppContextUtil.getSpringBean(AbstractStorageService.class).generateAssetRelativeLink(resourceId);
    }
}
