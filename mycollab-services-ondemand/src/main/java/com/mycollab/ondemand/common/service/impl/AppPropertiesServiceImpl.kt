package com.mycollab.ondemand.common.service.impl

import com.mycollab.common.service.AppPropertiesService
import com.mycollab.configuration.ServerConfiguration
import com.mycollab.core.utils.DateTimeUtils
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.InitializingBean
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.IOException
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.util.*

/**
 * @author MyCollab Ltd
 * @since 5.2.12
 */
@Service
class AppPropertiesServiceImpl : AppPropertiesService, InitializingBean {

    private lateinit var properties: Properties

    @Autowired
    private lateinit var serverConfiguration: ServerConfiguration

    override val sysId: String
        get() = properties.getProperty("id", "${UUID.randomUUID()}${LocalDateTime.now().toEpochSecond(ZoneOffset.UTC)}")

    override val startDate: LocalDate
        get() {
            return try {
                val dateValue = properties.getProperty("startdate")
                DateTimeUtils.convertDateByString(dateValue, "yyyy-MM-dd'T'HH:mm:ss")
            } catch (e: Exception) {
                LocalDate.now()
            }
        }

    override val edition: String
        get() = "Cloud"

    @Throws(Exception::class)
    override fun afterPropertiesSet() {
        try {
            val homeFolder = serverConfiguration.getHomeDir()
            val sysFile = File(homeFolder, ".app.properties")
            properties = Properties()
            if (sysFile.isFile && sysFile.exists()) {
                properties.load(FileInputStream(sysFile))
                val startDate = properties.getProperty("startdate")
                if (startDate == null) {
                    properties.setProperty("startdate", DateTimeUtils.formatDateToW3C(LocalDate.now()))
                    properties.store(FileOutputStream(sysFile), "")
                }
            } else {
                properties.setProperty("id", "${UUID.randomUUID()}${LocalDateTime.now().toEpochSecond(ZoneOffset.UTC)}")
                properties.setProperty("startdate", DateTimeUtils.formatDateToW3C(LocalDate.now()))
                properties.store(FileOutputStream(sysFile), "")
            }
        } catch (e: IOException) {
            LOG.error("Error", e)
        }
    }

    companion object {
        private val LOG = LoggerFactory.getLogger(AppPropertiesServiceImpl::class.java)
    }
}