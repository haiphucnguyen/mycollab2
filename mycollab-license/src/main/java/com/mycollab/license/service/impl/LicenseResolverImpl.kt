package com.mycollab.license.service.impl

import com.javax0.license3j.licensor.License
import com.mycollab.common.service.AppPropertiesService
import com.mycollab.configuration.ServerConfiguration
import com.mycollab.core.MyCollabException
import com.mycollab.core.UserInvalidInputException
import com.mycollab.core.Version
import com.mycollab.core.utils.FileUtils
import com.mycollab.license.LicenseInfo
import com.mycollab.license.LicenseType
import com.mycollab.license.service.LicenseResolver
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.InitializingBean
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import java.io.*
import java.time.Duration
import java.time.LocalDate
import java.time.Period
import java.time.format.DateTimeFormatter
import java.util.*

/**
 * @author MyCollab Ltd
 * @since 5.2.6
 */
@Service
class LicenseResolverImpl(private val serverConfiguration: ServerConfiguration,
                          private val appPropertiesService: AppPropertiesService) : LicenseResolver, InitializingBean {

    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")

    private lateinit var _licenseInfo: LicenseInfo
    override var licenseInfo: LicenseInfo? = null
        get() = _licenseInfo

    private val licenseFile: File
        get() {
            val homeDir = serverConfiguration.getHomeDir()
            val potentialOldFile = File(homeDir, "mycollab.lic")
            val licenseFile = File(homeDir, ".mycollab.lic")
            if (potentialOldFile.exists() && !licenseFile.exists()) {
                potentialOldFile.renameTo(licenseFile)
            }
            return licenseFile
        }

    @Throws(Exception::class)
    override fun afterPropertiesSet() {
        if (appPropertiesService.edition != "Cloud") {
            val licenseFile = licenseFile
            if (licenseFile.isFile && licenseFile.exists()) {
                val licenseBytes = org.apache.commons.io.FileUtils.readFileToByteArray(licenseFile)
                checkLicenseInfo(licenseBytes, false)
            } else {
                acquireALicense()
            }
        }
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
        val startDate = LocalDate.from(appPropertiesService.startDate)
        val now = LocalDate.now()
        val days = Period.between(startDate, now).days
        val edition = appPropertiesService.edition
        val restTemplate = RestTemplate()
        try {
            val licenseRequest = restTemplate.postForObject(serverConfiguration.getApiUrl("order/register-trial"), null, String::class.java)
            checkAndSaveLicenseInfo(licenseRequest!!)
        } catch (e: Exception) {
            LOG.error("Can not retrieve a trial license", e)
            _licenseInfo = createTempValidLicense(30L - days)
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
            val expireDate = LocalDate.parse(prop.getProperty("expireDate"), formatter)
            if (Version.getReleasedDate().isAfter(expireDate)) {
                return createInvalidLicense()
            }
            val newLicenseInfo = LicenseInfo()
            newLicenseInfo.customerId = prop.getProperty("customerId")
            newLicenseInfo.licenseType = LicenseType.valueOf(prop.getProperty("licenseType"))
            newLicenseInfo.expireDate = expireDate
            newLicenseInfo.issueDate = LocalDate.parse(prop.getProperty("issueDate"), formatter)
            newLicenseInfo.licenseOrg = prop.getProperty("licenseOrg")
            newLicenseInfo.maxUsers = Integer.parseInt(prop.getProperty("maxUsers", "10"))
            if (isSave) {
                if (newLicenseInfo.isExpired) {
                    throw UserInvalidInputException("License is expired")
                }
                if (newLicenseInfo.isTrial) {
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
        tmpLicenseInfo.expireDate = LocalDate.now()
        tmpLicenseInfo.issueDate = LocalDate.now()
        tmpLicenseInfo.licenseOrg = ""
        tmpLicenseInfo.maxUsers = 1
        tmpLicenseInfo.licenseType = LicenseType.INVALID
        return tmpLicenseInfo
    }

    private fun createTempValidLicense(days: Long): LicenseInfo {
        val now = LocalDate.now()
        val tmpLicenseInfo = LicenseInfo()
        tmpLicenseInfo.customerId = ""
        tmpLicenseInfo.expireDate = now.plusDays(days)
        tmpLicenseInfo.issueDate = now
        tmpLicenseInfo.licenseOrg = ""
        tmpLicenseInfo.maxUsers = 10
        tmpLicenseInfo.licenseType = LicenseType.PRO_TRIAL
        return tmpLicenseInfo
    }

    companion object {
        private val LOG = LoggerFactory.getLogger(LicenseResolverImpl::class.java)
    }
}
