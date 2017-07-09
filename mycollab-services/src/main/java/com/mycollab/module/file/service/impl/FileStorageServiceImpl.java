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
package com.mycollab.module.file.service.impl;

import com.mycollab.configuration.SiteConfiguration;
import com.mycollab.core.Version;
import com.mycollab.core.utils.FileUtils;
import com.mycollab.module.file.service.AbstractStorageService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import java.io.File;

/**
 * @author MyCollab Ltd
 * @since 5.5.0
 */
@Service
public class FileStorageServiceImpl extends AbstractStorageService implements InitializingBean {

    @Override
    public void afterPropertiesSet() throws Exception {
        File baseContentFolder = FileUtils.getHomeFolder();
        File avatarFolder = new File(baseContentFolder, "avatar");
        File logoFolder = new File(baseContentFolder, "logo");
        FileUtils.mkdirs(avatarFolder);
        FileUtils.mkdirs(logoFolder);
    }

    @Override
    public String generateAssetRelativeLink(String resourceId) {
        return String.format("%s%s?v=%s", serverConfiguration.getCdnUrl(), resourceId, Version.getVersion());
    }
}
