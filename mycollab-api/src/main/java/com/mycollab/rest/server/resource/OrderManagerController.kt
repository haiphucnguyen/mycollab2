package com.mycollab.rest.server.resource

import com.google.common.base.MoreObjects
import com.mycollab.configuration.EnDecryptHelper
import com.mycollab.core.MyCollabException
import com.mycollab.core.utils.DateTimeUtils
import com.mycollab.license.LicenseInfo
import com.mycollab.license.LicenseType
import com.mycollab.ondemand.module.billing.dao.ProEditionInfoMapper
import com.mycollab.ondemand.module.billing.domain.ProEditionInfo
import com.mycollab.ondemand.module.billing.domain.ProEditionInfoExample
import com.verhas.licensor.License
import io.swagger.annotations.Api
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.*
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.StringWriter
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*

/**
 * @author MyCollab Ltd
 * @since 5.2.5
 */
@Api(value = "Order", tags = ["Billing"])
@RestController
@RequestMapping(path = ["/order"])
class OrderManagerController(private val proEditionMapper: ProEditionInfoMapper) {

    @PostMapping(path = ["/generatelicense"], headers = ["Content-Type=application/x-www-form-urlencoded", "Accept=application/json"])
    @CrossOrigin
    @Throws(Exception::class)
    fun generateLicense(@RequestParam("name") name: String,
                        @RequestParam("quantity") quantity: String,
                        @RequestParam("reference") reference: String,
                        @RequestParam("email") email: String,
                        @RequestParam(value = "company", required = false) company: String,
                        @RequestParam(value = "referrer", required = false) referrer: String,
                        @RequestParam("internalProductName") internalProductName: String,
                        @RequestParam(value = "tags", required = false) tags: String,
                        @RequestParam(value = "tagValues", required = false) tagValues: String,
                        @RequestParam(value = "sourceKey", required = false) sourceKey: String,
                        @RequestParam(value = "sourceCampaign", required = false) sourceCampaign: String,
                        @RequestParam(value = "test", required = false) test: String,
                        @RequestParam(value = "subscriptionReference", required = false) subscriptionReference: String): String {
        LOG.info("Generate license email: $email company: $company")
        val now = LocalDate.now()
        val proEditionInfo = ProEditionInfo()
        proEditionInfo.internalproductname = internalProductName
        proEditionInfo.email = email
        proEditionInfo.name = name
        proEditionInfo.quantity = 1
        proEditionInfo.orderid = reference
        proEditionInfo.issuedate = now
        proEditionInfo.type = "New"
        val customerId = proEditionMapper.insertAndReturnKey(proEditionInfo)

        val licenseInfo = LicenseInfo()
        licenseInfo.customerId = EnDecryptHelper.encryptText("" + customerId!!)
        licenseInfo.licenseType = LicenseType.PRO
        if (internalProductName.contains("Growing")) {
            licenseInfo.maxUsers = 10
        } else {
            licenseInfo.maxUsers = 9999
        }

        licenseInfo.issueDate = now
        licenseInfo.licenseOrg = MoreObjects.firstNonNull(company, "Default")
        licenseInfo.expireDate = now.plusYears(1)
        val license = encode(licenseInfo)
        val result = StringBuilder()
        result.append("Mime-Version: 1.0\n")
                .append("Content-Type: multipart/mixed; boundary=license\n\n\n")
                .append("--license\n")
                .append("Content-Disposition: inline; filename=mycollab.lic\n")
                .append("Content-Type: text/plain\n")
                .append("Content-Transfer-Encoding: 8BIT\n\n")
                .append(license)
                .append("\n--license--")
        return result.toString()
    }

    @RequestMapping(path = ["/completed"], method = [(RequestMethod.POST)], headers = ["Content-Type=application/x-www-form-urlencoded", "Accept=application/json"])
    @CrossOrigin
    @Throws(Exception::class)
    fun orderCompletedCallback(@RequestParam("AddressCity") addressCity: String,
                               @RequestParam("AddressCountry") addressCountry: String,
                               @RequestParam("AddressPostalCode") addressPostalCode: String,
                               @RequestParam("AddressStreet1") addressStreet1: String,
                               @RequestParam("AddressStreet2") addressStreet2: String,
                               @RequestParam("CustomerCompany") customerCompany: String,
                               @RequestParam("CustomerEmail") customerEmail: String,
                               @RequestParam("CustomerName") customerName: String,
                               @RequestParam("CustomerPhone") customerPhone: String,
                               @RequestParam("OrderID") orderId: String,
                               @RequestParam("OrderProductNames") orderProductNames: String,
                               @RequestParam("OrderReferrer") orderReferrer: String,
                               @RequestParam("OrderSubTotalUSD") orderSubTotalUSD: String): String {
        LOG.info("Complete order name: $customerName email: $customerEmail id: $orderId")
        val ex = ProEditionInfoExample()
        ex.createCriteria().andOrderidEqualTo(orderId)
        val proEditionInfos = proEditionMapper.selectByExample(ex)
        if (proEditionInfos.size == 1) {
            val proEditionInfo = proEditionInfos[0]
            proEditionInfo.address1 = addressStreet1
            proEditionInfo.address2 = addressStreet2
            proEditionInfo.city = addressCity
            proEditionInfo.country = addressCountry
            proEditionInfo.phone = customerPhone
            proEditionInfo.cost = java.lang.Double.parseDouble(orderSubTotalUSD)
            proEditionInfo.company = customerCompany
            proEditionInfo.type = "New"
            proEditionMapper.updateByPrimaryKeySelective(proEditionInfo)
        } else {
            LOG.error("There is invalid order $orderId")
        }
        return "Ok"
    }

    @RequestMapping(path = ["/register-trial"], method = [(RequestMethod.POST)], headers = ["Content-Type=application/x-www-form-urlencoded", "Accept=application/json"])
    @CrossOrigin
    fun registerTrial(): String {
        LOG.info("Process trial request")
        val info = LicenseInfo()
        info.customerId = "0"
        info.licenseType = LicenseType.PRO_TRIAL
        info.expireDate = LocalDate().plusDays(30).toDate()
        info.issueDate = LocalDate().toDate()
        info.licenseOrg = "MyCollab"
        info.maxUsers = 30
        return encode(info)
    }

    companion object {
        private val LOG = LoggerFactory.getLogger(OrderManagerController::class.java)

        private fun encode(licenseInfo: LicenseInfo): String {
            try {
                val prop = Properties()
                prop.setProperty("customerId", licenseInfo.customerId)
                prop.setProperty("licenseType", licenseInfo.licenseType.name)
                prop.setProperty("licenseOrg", licenseInfo.licenseOrg)
                prop.setProperty("expireDate", DateTimeUtils.formatDateToW3C(licenseInfo.expireDate))
                prop.setProperty("issueDate", DateTimeUtils.formatDateToW3C(licenseInfo.issueDate))
                prop.setProperty("maxUsers", licenseInfo.maxUsers.toString() + "")
                val outStream = StringWriter()
                prop.store(outStream, "")
                val licenseStrInfo = outStream.toString()

                val license = License()
                license.setLicense(licenseStrInfo)
                val privateStream = OrderManagerController::class.java.classLoader.getResourceAsStream("secring.gpg")
                license.loadKey(privateStream, "MyCollab Ltd (The private key for MyCollab license) <hainguyen@mycollab.com>")
                return license.encodeLicense("HanLong1979")
            } catch (e: Exception) {
                throw MyCollabException("Can not generate license", e)
            }

        }

        private fun decode(encodeStr: String): Properties {
            try {
                val license = License()
                val publicStream = OrderManagerController::class.java.classLoader.getResourceAsStream("pubring.gpg")
                license.loadKeyRing(publicStream, null)
                license.setLicenseEncoded(encodeStr)
                val outputStream = ByteArrayOutputStream()
                license.dumpLicense(outputStream)
                outputStream.flush()
                val prop = Properties()
                prop.load(ByteArrayInputStream(outputStream.toByteArray()))
                return prop
            } catch (e: Exception) {
                throw MyCollabException("Failed to check MyCollab license", e)
            }

        }
/*
        @JvmStatic
        fun main(args: Array<String>) {
            val licenseInfo = LicenseInfo()
            licenseInfo.customerId = "10"
            licenseInfo.expireDate = DateTime().plusDays(365).toDate()
            licenseInfo.issueDate = DateTime().toDate()
            licenseInfo.maxUsers = 10
            licenseInfo.licenseType = LicenseType.PRO
            licenseInfo.licenseOrg = "On Call Computer Solutions"
            println(encode(licenseInfo))
        } */
    }
}
