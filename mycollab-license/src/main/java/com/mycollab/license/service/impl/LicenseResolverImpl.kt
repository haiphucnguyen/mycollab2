package com.mycollab.license.service.impl

import com.mycollab.common.service.AppPropertiesService
import com.mycollab.configuration.ServerConfiguration
import com.mycollab.core.MyCollabException
import com.mycollab.core.UserInvalidInputException
import com.mycollab.core.Version
import com.mycollab.core.utils.DateTimeUtils
import com.mycollab.core.utils.FileUtils
import com.mycollab.license.LicenseInfo
import com.mycollab.license.LicenseType
import com.mycollab.license.service.LicenseResolver
import com.verhas.licensor.License
import org.joda.time.DateTime
import org.joda.time.Duration
import org.joda.time.LocalDate
import org.joda.time.LocalDateTime
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.InitializingBean
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import java.io.*
import java.util.*

/**
 * @author MyCollab Ltd
 * @since 5.2.6
 */
@Service
class LicenseResolverImpl(private val serverConfiguration: ServerConfiguration) : LicenseResolver, AppPropertiesService, InitializingBean {

    private lateinit var properties: Properties

    private lateinit var _licenseInfo: LicenseInfo
    override var licenseInfo: LicenseInfo? = null
        get() {
            return _licenseInfo
        }

    private val licenseFile: File
        get() {
            val homeDir = FileUtils.homeFolder
            val potentialOldFile = File(homeDir, "mycollab.lic")
            val licenseFile = File(homeDir, ".mycollab.lic")
            if (potentialOldFile.exists() && !licenseFile.exists()) {
                potentialOldFile.renameTo(licenseFile)
            }
            return licenseFile
        }

    override val sysId: String
        get() = properties.getProperty("id", UUID.randomUUID().toString() + LocalDateTime().millisOfSecond)

    override val startDate: Date
        get() {
            try {
                val dateValue = properties.getProperty("startdate")
                return DateTimeUtils.convertDateByString(dateValue, "yyyy-MM-dd'T'HH:mm:ss")
            } catch (e: Exception) {
                return GregorianCalendar().time
            }

        }

    override val edition: String
        get() = "Premium"

    @Throws(Exception::class)
    override fun afterPropertiesSet() {
        val homeFolder = FileUtils.homeFolder
        val sysFile = File(homeFolder, ".app.properties")
        properties = Properties()
        try {
            if (sysFile.isFile && sysFile.exists()) {
                properties.load(FileInputStream(sysFile))
                val startDate = properties.getProperty("startdate")
                if (startDate == null) {
                    properties.setProperty("startdate", DateTimeUtils.formatDateToW3C(GregorianCalendar().time))
                }
            } else {
                properties.setProperty("id", UUID.randomUUID().toString() + LocalDateTime().millisOfSecond)
                properties.setProperty("startdate", DateTimeUtils.formatDateToW3C(GregorianCalendar().time))
            }
        } catch (e: IOException) {
            LOG.error("Error", e)
        }

        val licenseFile = licenseFile
        if (licenseFile.isFile && licenseFile.exists()) {
            val licenseBytes = org.apache.commons.io.FileUtils.readFileToByteArray(licenseFile)
            checkLicenseInfo(licenseBytes, false)
        } else {
            acquireALicense()
        }

        properties.store(FileOutputStream(sysFile), "")
    }

    override fun checkAndSaveLicenseInfo(licenseInputText: String) {
        try {
            checkLicenseInfo(licenseInputText.toByteArray(charset("UTF-8")), true)
        } catch (e: UnsupportedEncodingException) {
            throw MyCollabException("Error", e)
        }

    }

    private fun acquireALicense() {
        LOG.info("Acquire the trial license")
        val startDate = DateTime(startDate)
        val now = DateTime()
        val days = Duration(startDate, now).standardDays.toInt()
        val edition = properties.getProperty("edition", "Community")
        if (edition != edition) {
            properties.setProperty("edition", edition)
            val restTemplate = RestTemplate()
            try {
                val licenseRequest = restTemplate.postForObject(serverConfiguration!!.getApiUrl("order/register-trial"), null, String::class.java)
                checkAndSaveLicenseInfo(licenseRequest)
            } catch (e: Exception) {
                LOG.error("Can not retrieve a trial license", e)
                _licenseInfo = createTempValidLicense(30 - days)
            }

        } else {
            _licenseInfo = createInvalidLicense()
        }
    }

    override fun checkLicenseInfo(licenseBytes: ByteArray, isSave: Boolean): LicenseInfo {
        try {
            val license = License()
            val publicStream = LicenseResolverImpl::class.java.classLoader.getResourceAsStream("pubring.gpg")
            license.loadKeyRing(publicStream, null)
            license.setLicenseEncoded(ByteArrayInputStream(licenseBytes), "UTF-8")
            val outputStream = ByteArrayOutputStream()
            license.dumpLicense(outputStream)
            outputStream.flush()
            val prop = Properties()
            val bytes = outputStream.toByteArray()
            prop.load(ByteArrayInputStream(bytes))
            val expireDate = DateTime(DateTimeUtils.parseDateByW3C(prop.getProperty("expireDate")))
            if (Version.getReleasedDate().isAfter(expireDate)) {
                return createInvalidLicense()
            }
            val newLicenseInfo = LicenseInfo()
            newLicenseInfo.customerId = prop.getProperty("customerId")
            newLicenseInfo.licenseType = LicenseType.valueOf(prop.getProperty("licenseType"))
            newLicenseInfo.expireDate = expireDate.toDate()
            newLicenseInfo.issueDate = DateTimeUtils.parseDateByW3C(prop.getProperty("issueDate"))
            newLicenseInfo.licenseOrg = prop.getProperty("licenseOrg")
            newLicenseInfo.maxUsers = Integer.parseInt(prop.getProperty("maxUsers", "10"))
            if (isSave) {
                if (newLicenseInfo.isExpired) {
                    throw UserInvalidInputException("License is expired")
                }
                if (_licenseInfo != null && newLicenseInfo.isTrial) {
                    throw UserInvalidInputException("You can not enter another trial license during trial period")
                }
                val licenseFile = licenseFile
                val fileOutputStream = FileOutputStream(licenseFile)
                fileOutputStream.write(licenseBytes)
                fileOutputStream.close()
            }
            _licenseInfo = newLicenseInfo
        } catch (e: Exception) {
            _licenseInfo = createInvalidLicense()
        }

        return _licenseInfo
    }

    private fun createInvalidLicense(): LicenseInfo {
        val tmpLicenseInfo = LicenseInfo()
        tmpLicenseInfo.customerId = ""
        tmpLicenseInfo.expireDate = GregorianCalendar().time
        tmpLicenseInfo.issueDate = GregorianCalendar().time
        tmpLicenseInfo.licenseOrg = ""
        tmpLicenseInfo.maxUsers = 1
        tmpLicenseInfo.licenseType = LicenseType.INVALID
        return tmpLicenseInfo
    }

    private fun createTempValidLicense(days: Int): LicenseInfo {
        val now = LocalDate()
        val tmpLicenseInfo = LicenseInfo()
        tmpLicenseInfo.customerId = ""
        tmpLicenseInfo.expireDate = now.plusDays(days).toDate()
        tmpLicenseInfo.issueDate = now.toDate()
        tmpLicenseInfo.licenseOrg = ""
        tmpLicenseInfo.maxUsers = 10
        tmpLicenseInfo.licenseType = LicenseType.PRO_TRIAL
        return tmpLicenseInfo
    }

    companion object {
        private val LOG = LoggerFactory.getLogger(LicenseResolverImpl::class.java)
    }
}
