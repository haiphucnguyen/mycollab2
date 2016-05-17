package com.esofthead.mycollab.ondemand.module.file.service;

import com.esofthead.mycollab.cache.IgnoreCacheClass;
import com.esofthead.mycollab.configuration.ApplicationProperties;
import com.esofthead.mycollab.core.persistence.service.IService;
import com.esofthead.mycollab.module.file.service.RawContentService;
import com.esofthead.mycollab.module.file.service.impl.FileRawContentServiceImpl;
import com.esofthead.mycollab.ondemand.module.file.service.impl.S3RawContentServiceImpl;
import org.springframework.beans.factory.config.AbstractFactoryBean;
import org.springframework.stereotype.Service;

import static com.esofthead.mycollab.configuration.StorageFactory.FILE_STORAGE_SYSTEM;
import static com.esofthead.mycollab.configuration.StorageFactory.S3_STORAGE_SYSTEM;

/**
 * Factory spring bean to solve resolution of MyCollab raw content service
 * should be <code>FileRawContentServiceImpl</code> if MyCollab is installed in
 * local server (dev, community or premium mode) or
 * <code>S3RawContentServiceImpl</code> if MyCollab is installed on MyCollab
 * server.
 */
@Service(value = "rawContentService")
@IgnoreCacheClass
public class RawContentServiceFactoryBean extends AbstractFactoryBean<RawContentService> implements IService {

    @Override
    protected RawContentService createInstance() throws Exception {
        String storageSystem = ApplicationProperties.getString(ApplicationProperties.STORAGE_SYSTEM, FILE_STORAGE_SYSTEM);
        return S3_STORAGE_SYSTEM.equals(storageSystem) ? new S3RawContentServiceImpl() : new FileRawContentServiceImpl();
    }

    @Override
    public Class<RawContentService> getObjectType() {
        return RawContentService.class;
    }

}
