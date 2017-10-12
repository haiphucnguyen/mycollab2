package com.mycollab.premium.module.file.service

import com.mycollab.cache.IgnoreCacheClass
import com.mycollab.db.persistence.service.IService
import com.mycollab.module.file.service.RawContentService
import com.mycollab.module.file.service.impl.FileRawContentServiceImpl
import org.springframework.beans.factory.config.AbstractFactoryBean
import org.springframework.stereotype.Service

/**
 * Factory spring bean to solve resolution of MyCollab raw content service
 * should be `FileRawContentServiceImpl` if MyCollab is installed in
 * local server (dev, community or premium mode) or
 * `AmazonRawContentServiceImpl` if MyCollab is installed on MyCollab
 * server.
 */
@Service
@IgnoreCacheClass
class RawContentServiceFactoryBean : AbstractFactoryBean<RawContentService>(), IService {

    @Throws(Exception::class)
    override fun createInstance(): RawContentService = FileRawContentServiceImpl()

    override fun getObjectType(): Class<RawContentService> = RawContentService::class.java
}
