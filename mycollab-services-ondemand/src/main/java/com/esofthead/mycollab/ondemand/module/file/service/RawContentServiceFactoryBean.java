package com.esofthead.mycollab.ondemand.module.file.service;

import com.esofthead.mycollab.cache.IgnoreCacheClass;
import com.esofthead.mycollab.core.persistence.service.IService;
import com.esofthead.mycollab.module.file.service.RawContentService;
import com.esofthead.mycollab.ondemand.module.file.service.impl.S3RawContentServiceImpl;
import org.springframework.beans.factory.config.AbstractFactoryBean;
import org.springframework.stereotype.Service;

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
        return new S3RawContentServiceImpl();
    }

    @Override
    public Class<RawContentService> getObjectType() {
        return RawContentService.class;
    }

}
