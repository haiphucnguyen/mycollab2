package com.mycollab.rest.server.resource

import com.mycollab.configuration.EnDecryptHelper
import com.mycollab.core.BroadcastMessage
import com.mycollab.core.Broadcaster
import com.mycollab.core.MyCollabException
import com.mycollab.module.billing.AccountStatusConstants
import com.mycollab.module.user.dao.BillingAccountMapper
import com.mycollab.module.user.domain.BillingAccount
import com.mycollab.module.user.domain.BillingAccountExample
import com.mycollab.ondemand.module.billing.dao.BillingSubscriptionHistoryMapper
import com.mycollab.ondemand.module.billing.dao.BillingSubscriptionMapper
import com.mycollab.ondemand.module.billing.dao.BillingSubscriptionMapperExt
import com.mycollab.ondemand.module.billing.domain.BillingSubscription
import com.mycollab.ondemand.module.billing.domain.BillingSubscriptionExample
import com.mycollab.ondemand.module.billing.domain.BillingSubscriptionHistory
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.*
import java.util.*

/**
 * @author MyCollab Ltd
 * @since 5.3.4
 */
@Api(value = "subscription", tags = ["Billing"])
@RestController
@RequestMapping(path = ["/subscription"])
class SubscriptionManagerController(private val subscriptionMapper: BillingSubscriptionMapper,
                                    private val subscriptionMapperExt: BillingSubscriptionMapperExt,
                                    private val subscriptionHistoryMapper: BillingSubscriptionHistoryMapper,
                                    private val billingAccountMapper: BillingAccountMapper) {

    @ApiOperation(value = "Register the subscription", response = String::class)
    @RequestMapping(path = ["/register"], method = [(RequestMethod.POST)], headers = ["Content-Type=application/x-www-form-urlencoded", "Accept=application/json"])
    @CrossOrigin
    fun registerEE(@RequestParam("email") email: String,
                   @RequestParam("internalProductName") internalProductName: String,
                   @RequestParam("name") name: String,
                   @RequestParam("quantity") quantity: Int,
                   @RequestParam("referrer") referrer: String,
                   @RequestParam("reference") reference: String,
                   @RequestParam("subscriptionReference") subscriptionReference: String,
                   @RequestParam(value = "test", required = false) test: String,
                   @RequestParam("security_request_hash") security_request_hash: String): String {
        try {
            val decryptReferrer = EnDecryptHelper.decryptTextWithEncodeFriendly(referrer)
            val arr = decryptReferrer.split(";".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
            val subscription = BillingSubscription()
            subscription.email = email
            subscription.accountid = Integer.parseInt(arr[0])
            subscription.name = name
            subscription.billingid = Integer.parseInt(arr[1])
            subscription.subreference = subscriptionReference
            subscription.subscriptioncustomerurl = ""
            subscription.createdtime = DateTime().toDate()
            subscription.status = "Active"
            subscriptionMapper.insert(subscription)
            tempVariables.put(subscriptionReference, reference)
            return "Ok"
        } catch (e: Exception) {
            val errorMsg = StringBuilder()
            errorMsg.append("email: ").append(email).append("\n")
            errorMsg.append("internalProductName: ").append(internalProductName).append("\n")
            errorMsg.append("name: ").append(name).append("\n")
            errorMsg.append("referrer: ").append(referrer).append("\n")
            errorMsg.append("subscriptionReference: ").append(subscriptionReference).append("\n")
            LOG.error("Error in subscribe account $errorMsg")
            throw MyCollabException(e)
        }

    }

    @RequestMapping(path = ["/activated"], method = [(RequestMethod.POST)],
            headers = ["Content-Type=application/x-www-form-urlencoded", "Accept=application/json"])
    @CrossOrigin
    @Throws(Exception::class)
    fun activateSubscription(@RequestParam("SubscriptionReference") subscriptionReference: String,
                             @RequestParam("SubscriptionReferrer") subscriptionReferrer: String,
                             @RequestParam("NextPeriodDate") nextPeriodDate: String,
                             @RequestParam("ProductName") productName: String,
                             @RequestParam("TotalPrice") totalPrice: String,
                             @RequestParam("CustomerFullName") customerFullName: String,
                             @RequestParam("Email") email: String,
                             @RequestParam("CompanyName") companyName: String,
                             @RequestParam("Phone") phone: String,
                             @RequestParam("SubscriptionCustomerUrl") subscriptionCustomerUrl: String): String {
        try {
            val decryptReferrer = EnDecryptHelper.decryptTextWithEncodeFriendly(subscriptionReferrer)
            val arr = decryptReferrer.split(";".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
            val sAccountId = Integer.parseInt(arr[0])
            val ex = BillingSubscriptionExample()
            ex.createCriteria().andSubreferenceEqualTo(subscriptionReference).andAccountidEqualTo(sAccountId)
            val subscriptions = subscriptionMapper.selectByExample(ex)
            if (subscriptions.size == 1) {
                val subscription = subscriptions[0]
                val subscriptionHistory = BillingSubscriptionHistory()
                subscriptionHistory.subscriptionid = subscription.id
                var reference: String? = tempVariables[subscriptionReference]
                if (reference == null) {
                    reference = UUID.randomUUID().toString() + DateTime().millisOfSecond().get()
                } else {
                    tempVariables.remove(subscriptionReference)
                }
                subscriptionHistory.orderid = reference
                subscriptionHistory.createdtime = DateTime().toDate()
                subscriptionHistory.status = "Success"
                subscriptionHistory.expireddate = dateFormatter.parseLocalDate(nextPeriodDate).toDate()
                subscriptionHistory.productname = productName
                subscriptionHistory.totalprice = java.lang.Double.parseDouble(totalPrice)
                subscriptionHistoryMapper.insert(subscriptionHistory)

                subscription.company = companyName
                subscription.contactname = customerFullName
                subscription.phone = phone
                subscription.subscriptioncustomerurl = subscriptionCustomerUrl

                subscriptionMapper.updateByPrimaryKey(subscription)
                Broadcaster.broadcast(BroadcastMessage(BroadcastMessage.SCOPE_USER, subscription.accountid, null,""))

                val accountEx = BillingAccountExample()
                accountEx.createCriteria().andIdEqualTo(sAccountId)
                val billingAccount = BillingAccount()
                billingAccount.status = AccountStatusConstants.ACTIVE
                billingAccountMapper.updateByExampleSelective(billingAccount, accountEx)
            } else {
                LOG.error("Find subscription with id ${subscriptionReference}in account $sAccountId has count${subscriptions.size}")
            }
            return "Ok"
        } catch (e: Exception) {
            val errorMsg = StringBuilder()
            errorMsg.append("SubscriptionReference: ").append(subscriptionReference).append("\n")
            errorMsg.append("SubscriptionReferrer: ").append(subscriptionReferrer).append("\n")
            errorMsg.append("NextPeriodDate: ").append(nextPeriodDate).append("\n")
            errorMsg.append("ProductName: ").append(productName).append("\n")
            errorMsg.append("CompanyName: ").append(companyName).append("\n")
            errorMsg.append("Email: ").append(email).append("\n")
            LOG.error("Error in activate account $errorMsg")
            throw MyCollabException(e)
        }

    }

    @RequestMapping(path = ["/rebill-completed"], method = [(RequestMethod.POST)],
            headers = ["Content-Type=application/x-www-form-urlencoded", "Accept=application/json"])
    @CrossOrigin
    @Throws(Exception::class)
    fun reBillSubscription(@RequestParam("OrderId") orderId: String,
                           @RequestParam("OrderProductName") orderProductName: String,
                           @RequestParam(value = "CustomerName", required = false) customerFullName: String,
                           @RequestParam(value = "CustomerCompany", required = false) customerCompany: String,
                           @RequestParam("OrderReferrer") orderReferrer: String,
                           @RequestParam("NextPeriodDate") nextPeriodDate: String,
                           @RequestParam(value = "CustomerEmail", required = false) email: String,
                           @RequestParam("SubscriptionReference") subscriptionReference: String,
                           @RequestParam("OrderSubTotalUSD") orderSubTotalUSD: String,
                           @RequestParam("Status") status: String): String {
        val ex = BillingSubscriptionExample()
        ex.createCriteria().andSubreferenceEqualTo(subscriptionReference)
        val billingSubscriptions = subscriptionMapper.selectByExample(ex)
        if (billingSubscriptions.size == 1) {
            val billingSubscription = billingSubscriptions[0]
            val subscriptionHistory = BillingSubscriptionHistory()
            subscriptionHistory.subscriptionid = billingSubscription.id
            subscriptionHistory.orderid = orderId
            subscriptionHistory.createdtime = DateTime().toDate()
            subscriptionHistory.status = "Success"
            subscriptionHistory.expireddate = dateFormatter.parseLocalDate(nextPeriodDate).toDate()
            subscriptionHistory.productname = orderProductName
            subscriptionHistory.totalprice = java.lang.Double.parseDouble(orderSubTotalUSD)
            subscriptionHistoryMapper.insert(subscriptionHistory)

            val accountEx = BillingAccountExample()
            accountEx.createCriteria().andIdEqualTo(billingSubscription.accountid)
            val billingAccount = BillingAccount()
            billingAccount.status = AccountStatusConstants.ACTIVE
            billingAccountMapper.updateByExampleSelective(billingAccount, accountEx)
        } else {
            LOG.error("Find subscription with id ${subscriptionReference}in account has count ${billingSubscriptions.size}")
        }

        return "Ok"
    }

    @RequestMapping(path = ["/subscription-failed"], method = [(RequestMethod.POST)],
            headers = ["Content-Type=application/x-www-form-urlencoded", "Accept=application/json"])
    @CrossOrigin
    fun subscriptionFailedNotification(@RequestParam("SubscriptionReference") subscriptionReference: String,
                                       @RequestParam("SubscriptionEndDate") subscriptionEndDate: String): String {
        val ex = BillingSubscriptionExample()
        ex.createCriteria().andSubreferenceEqualTo(subscriptionReference)
        val billingSubscriptions = subscriptionMapper.selectByExample(ex)
        if (billingSubscriptions.size == 1) {
            val billingSubscription = billingSubscriptions[0]
            val subscriptionHistory = BillingSubscriptionHistory()
            subscriptionHistory.subscriptionid = billingSubscription.id
            subscriptionHistory.orderid = ""
            subscriptionHistory.createdtime = DateTime().toDate()
            subscriptionHistory.status = "Failed"
            val previousHistory = subscriptionMapperExt.getTheLastBillingSuccess(billingSubscription.accountid)
            if (previousHistory != null) {
                subscriptionHistory.expireddate = previousHistory.expireddate
                subscriptionHistory.productname = previousHistory.productname
            } else {
                subscriptionHistory.expireddate = DateTime().toDate()
            }

            subscriptionHistory.productname = ""
            subscriptionHistory.totalprice = -1.0
            subscriptionHistoryMapper.insert(subscriptionHistory)

            val accountEx = BillingAccountExample()
            accountEx.createCriteria().andIdEqualTo(billingSubscription.accountid)
            val billingAccount = BillingAccount()
            billingAccount.status = AccountStatusConstants.INVALID
            billingAccountMapper.updateByExampleSelective(billingAccount, accountEx)
        } else {
            LOG.error("Find subscription with id ${subscriptionReference}in account has count ${billingSubscriptions.size}")
        }
        return "Ok"
    }

    @RequestMapping(path = ["/deactivated"], method = [(RequestMethod.POST)], headers = ["Content-Type=application/x-www-form-urlencoded", "Accept=application/json"])
    fun subscriptionDeactivatedNotification(@RequestParam("SubscriptionCustomerUrl") subscriptionCustomerUrl: String,
                                            @RequestParam("SubscriptionEndDate") subscriptionEndDate: String,
                                            @RequestParam(value = "SubscriptionIsTest", required = false)
                                            subscriptionIsTest: String,
                                            @RequestParam(value = "SubscriptionQuantity", required = false)
                                            subscriptionQuantity: String,
                                            @RequestParam("SubscriptionReference") subscriptionReference: String,
                                            @RequestParam(value = "SubscriptionReferrer", required = false)
                                            subscriptionReferrer: String): String {
        val ex = BillingSubscriptionExample()
        ex.createCriteria().andSubreferenceEqualTo(subscriptionReference)
        val billingSubscriptions = subscriptionMapper.selectByExample(ex)
        if (billingSubscriptions.size == 1) {
            val subscription = billingSubscriptions[0]
            val accountEx = BillingAccountExample()
            accountEx.createCriteria().andIdEqualTo(subscription.accountid)
            val billingAccount = BillingAccount()
            billingAccount.status = AccountStatusConstants.INVALID
            billingAccountMapper.updateByExampleSelective(billingAccount, accountEx)
        } else {
            LOG.error("Find subscription with id ${subscriptionReference}in account has count ${billingSubscriptions.size}")
        }
        return "Ok"
    }

    companion object {
        private val LOG = LoggerFactory.getLogger(SubscriptionManagerController::class.java)

        private val dateFormatter = DateTimeFormat.forPattern("MMM d, yyyy")

        private val tempVariables = WeakHashMap<String, String>()
    }
}
