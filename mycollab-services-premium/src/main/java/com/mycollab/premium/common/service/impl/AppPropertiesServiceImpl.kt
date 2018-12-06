package com.mycollab.premium.common.service.impl

import com.mycollab.common.service.AppPropertiesService
import com.mycollab.core.utils.DateTimeUtils
import com.mycollab.core.utils.FileUtils
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.InitializingBean
import org.springframework.stereotype.Service
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.IOException
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*


/**
 * @author MyCollab Ltd
 * @since 5.2.12
 */
@Service
class AppPropertiesServiceImpl : AppPropertiesService, InitializingBean {

    private lateinit var properties: Properties

    // TODO: would get the miliseconds of now
    override val sysId: String
        get() = properties.getProperty("id", UUID.randomUUID().toString() + LocalDateTime.now())

    override val startDate: Date
        get() {
            return try {
                val dateValue = properties.getProperty("startdate")
                DateTimeUtils.convertDateByString(dateValue, "yyyy-MM-dd'T'HH:mm:ss")
            } catch (e: Exception) {
                GregorianCalendar().time
            }

        }

    override val edition: String
        get() = "Premium"

    @Throws(Exception::class)
    override fun afterPropertiesSet() {
        try {
            val homeFolder = FileUtils.homeFolder
            val sysFile = File(homeFolder, ".app.properties")
            properties = Properties()
            if (sysFile.isFile && sysFile.exists()) {
                properties.load(FileInputStream(sysFile))
                val startDate = properties.getProperty("startdate")
                if (startDate == null) {
                    properties.setProperty("startdate", DateTimeUtils.formatDateToW3C(LocalDate.now()))
                }
                properties.setProperty("edition", edition)
                properties.store(FileOutputStream(sysFile), "")
            } else {
                // TODO: would get the miliseconds of now
//                properties.setProperty("id", UUID.randomUUID().toString() + LocalDateTime().millisOfSecond)
                properties.setProperty("startdate", DateTimeUtils.formatDateToW3C(LocalDate.now()))
                properties.setProperty("edition", edition)
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