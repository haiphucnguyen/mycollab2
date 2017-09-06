package com.mycollab.premium.module.file.service;

import com.mycollab.cache.IgnoreCacheClass;
import com.mycollab.db.persistence.service.IService;
import com.mycollab.module.file.service.RawContentService;
import com.mycollab.module.file.service.impl.FileRawContentServiceImpl;
import org.springframework.beans.factory.config.AbstractFactoryBean;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

/**
 * Factory spring bean to solve resolution of MyCollab raw content service
 * should be <code>FileRawContentServiceImpl</code> if MyCollab is installed in
 * local server (dev, community or premium mode) or
 * <code>AmazonRawContentServiceImpl</code> if MyCollab is installed on MyCollab
 * server.
 */
@Service(value = "rawContentService")
@Profile("production")
@IgnoreCacheClass
public class RawContentServiceFactoryBean extends AbstractFactoryBean<RawContentService> implements IService {

    @Override
    protected RawContentService createInstance() throws Exception {
        return new FileRawContentServiceImpl();
    }

    @Override
    public Class<RawContentService> getObjectType() {
        return RawContentService.class;
    }
}
