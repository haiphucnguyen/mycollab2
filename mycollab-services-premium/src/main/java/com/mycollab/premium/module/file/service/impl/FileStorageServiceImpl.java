package com.mycollab.premium.module.file.service.impl;

import com.mycollab.configuration.ServerConfiguration;
import com.mycollab.core.Version;
import com.mycollab.core.utils.FileUtils;
import com.mycollab.module.file.service.AbstractStorageService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;

/**
 * @author MyCollab Ltd
 * @since 5.5.0
 */
@Service
public class FileStorageServiceImpl extends AbstractStorageService implements InitializingBean {

    @Autowired
    private ServerConfiguration serverConfiguration;

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
