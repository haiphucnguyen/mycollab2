package com.mycollab.ondemand.module.file.service

import com.mycollab.cache.IgnoreCacheClass
import com.mycollab.configuration.ServerConfiguration
import com.mycollab.db.persistence.service.IService
import com.mycollab.module.file.service.RawContentService
import com.mycollab.module.file.service.impl.FileRawContentServiceImpl
import com.mycollab.ondemand.module.file.service.impl.S3RawContentServiceImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.config.AbstractFactoryBean
import org.springframework.stereotype.Service

import com.mycollab.configuration.ServerConfiguration.Companion.STORAGE_S3

/**
 * Factory spring bean to solve resolution of MyCollab raw content service
 * should be `FileRawContentServiceImpl` if MyCollab is installed in
 * local server (dev, community or premium mode) or
 * `S3RawContentServiceImpl` if MyCollab is installed on MyCollab
 * server.
 */
@Service
@IgnoreCacheClass
class RawContentServiceFactoryBean(private val serverConfiguration: ServerConfiguration) : AbstractFactoryBean<RawContentService>(), IService {

    @Throws(Exception::class)
    override fun createInstance(): RawContentService {
        val storageSystem = serverConfiguration.storageSystem
        return if (STORAGE_S3 == storageSystem) S3RawContentServiceImpl() else FileRawContentServiceImpl()
    }

    override fun isSingleton(): Boolean = false

    override fun getObjectType(): Class<RawContentService> = RawContentService::class.java

}
