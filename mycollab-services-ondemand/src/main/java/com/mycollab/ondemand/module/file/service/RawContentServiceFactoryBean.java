package com.mycollab.ondemand.module.file.service;

import com.mycollab.cache.IgnoreCacheClass;
import com.mycollab.configuration.ServerConfiguration;
import com.mycollab.db.persistence.service.IService;
import com.mycollab.module.file.service.RawContentService;
import com.mycollab.module.file.service.impl.FileRawContentServiceImpl;
import com.mycollab.ondemand.module.file.service.impl.S3RawContentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AbstractFactoryBean;
import org.springframework.stereotype.Service;

import static com.mycollab.configuration.ServerConfiguration.STORAGE_S3;

/**
 * Factory spring bean to solve resolution of MyCollab raw content service
 * should be <code>FileRawContentServiceImpl</code> if MyCollab is installed in
 * local server (dev, community or premium mode) or
 * <code>S3RawContentServiceImpl</code> if MyCollab is installed on MyCollab
 * server.
 */
@Service
@IgnoreCacheClass
public class RawContentServiceFactoryBean extends AbstractFactoryBean<RawContentService> implements IService {

    @Autowired
    private ServerConfiguration serverConfiguration;

    @Override
    protected RawContentService createInstance() throws Exception {
        String storageSystem = serverConfiguration.getStorageSystem();
        return (STORAGE_S3.equals(storageSystem)) ? new S3RawContentServiceImpl() : new FileRawContentServiceImpl();
    }

    @Override
    public boolean isSingleton() {
        return false;
    }

    @Override
    public Class<RawContentService> getObjectType() {
        return RawContentService.class;
    }

}
