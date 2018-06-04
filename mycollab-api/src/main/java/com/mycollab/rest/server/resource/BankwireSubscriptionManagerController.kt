package com.mycollab.rest.server.resource

import com.mycollab.configuration.EnDecryptHelper
import com.mycollab.core.MyCollabException
import com.mycollab.module.billing.AccountStatusConstants
import com.mycollab.module.user.dao.BillingAccountMapper
import com.mycollab.module.user.domain.BillingAccount
import com.mycollab.module.user.domain.BillingAccountExample
import com.mycollab.ondemand.module.billing.dao.BillingSubscriptionHistoryMapper
import com.mycollab.ondemand.module.billing.dao.BillingSubscriptionMapper
import com.mycollab.ondemand.module.billing.domain.BillingSubscription
import com.mycollab.ondemand.module.billing.domain.BillingSubscriptionHistory
import org.joda.time.DateTime
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

/**
 * @author MyCollab Ltd
 * @since 5.4.2
 */
@RestController
@RequestMapping(path = ["/subscription"])
class BankwireSubscriptionManagerController(private val subscriptionMapper: BillingSubscriptionMapper,
                                            private val subscriptionHistoryMapper: BillingSubscriptionHistoryMapper,
                                            private val billingAccountMapper: BillingAccountMapper) {

    @RequestMapping(path = ["/bankwireMethod"], method = [(RequestMethod.POST)], headers = arrayOf("Content-Type=application/x-www-form-urlencoded", "Accept=application/json"))
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
        try {
            val decryptReferrer = EnDecryptHelper.decryptTextWithEncodeFriendly(orderReferrer)
            val arr = decryptReferrer!!.split(";".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
            val subscription = BillingSubscription()
            subscription.email = customerEmail
            subscription.accountid = Integer.parseInt(arr[0])
            subscription.name = orderProductNames
            subscription.billingid = Integer.parseInt(arr[1])
            subscription.subreference = orderId
            subscription.subscriptioncustomerurl = ""
            subscription.createdtime = DateTime().toDate()
            subscription.status = "Active"
            subscription.company = customerCompany
            subscription.contactname = customerName
            subscription.phone = customerPhone
            subscription.subscriptioncustomerurl = ""
            subscriptionMapper.insertAndReturnKey(subscription)

            val subscriptionHistory = BillingSubscriptionHistory()
            subscriptionHistory.subscriptionid = subscription.id
            subscriptionHistory.orderid = orderId
            subscriptionHistory.createdtime = DateTime().toDate()
            subscriptionHistory.status = "Success"
            subscriptionHistory.expireddate = DateTime().plusYears(1).toDate()
            subscriptionHistory.productname = orderProductNames
            subscriptionHistory.totalprice = java.lang.Double.parseDouble(orderSubTotalUSD)
            subscriptionHistoryMapper.insert(subscriptionHistory)

            val accountEx = BillingAccountExample()
            accountEx.createCriteria().andIdEqualTo(Integer.parseInt(arr[0]))
            val billingAccount = BillingAccount()
            billingAccount.status = AccountStatusConstants.ACTIVE
            billingAccount.paymentmethod = "Bankwire"
            billingAccountMapper.updateByExampleSelective(billingAccount, accountEx)

            return "Ok"
        } catch (e: Exception) {
            val errorMsg = StringBuilder().apply {
                append("customerCompany: ").append(customerCompany).append("\n")
                append("customerEmail: ").append(customerEmail).append("\n")
                append("customerName: ").append(customerName).append("\n")
                append("orderId: ").append(orderId).append("\n")
                append("orderReferrer: ").append(orderReferrer).append("\n")
            }
            LOG.error("Error white completing the order $errorMsg", e)
            throw MyCollabException(e)
        }

    }

    companion object {

        private val LOG = LoggerFactory.getLogger(BankwireSubscriptionManagerController::class.java)
    }
}
