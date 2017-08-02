package com.mycollab.rest.server.resource;

import com.google.common.eventbus.AsyncEventBus;

import com.mycollab.configuration.EnDecryptHelper;
import com.mycollab.core.BroadcastMessage;
import com.mycollab.core.Broadcaster;
import com.mycollab.core.MyCollabException;
import com.mycollab.module.billing.AccountStatusConstants;
import com.mycollab.module.user.dao.BillingAccountMapper;
import com.mycollab.module.user.domain.BillingAccount;
import com.mycollab.module.user.domain.BillingAccountExample;
import com.mycollab.ondemand.module.billing.dao.BillingSubscriptionHistoryMapper;
import com.mycollab.ondemand.module.billing.dao.BillingSubscriptionMapper;
import com.mycollab.ondemand.module.billing.dao.BillingSubscriptionMapperExt;
import com.mycollab.ondemand.module.billing.domain.BillingSubscription;
import com.mycollab.ondemand.module.billing.domain.BillingSubscriptionExample;
import com.mycollab.ondemand.module.billing.domain.BillingSubscriptionHistory;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.WeakHashMap;

/**
 * @author MyCollab Ltd
 * @since 5.3.4
 */
@RestController
@RequestMapping(path = "/subscription")
public class SubscriptionManagerController {
    private static Logger LOG = LoggerFactory.getLogger(SubscriptionManagerController.class);

    private static final DateTimeFormatter dateFormatter = DateTimeFormat.forPattern("MMM d, yyyy");

    private static Map<String, String> tempVariables = new WeakHashMap<>();

    @Autowired
    private BillingSubscriptionMapper subscriptionMapper;

    @Autowired
    private BillingSubscriptionMapperExt subscriptionMapperExt;

    @Autowired
    private BillingSubscriptionHistoryMapper subscriptionHistoryMapper;

    @Autowired
    private BillingAccountMapper billingAccountMapper;

    @Autowired
    private AsyncEventBus asyncEventBus;

    @RequestMapping(path = "/register", method = RequestMethod.POST, headers =
            {"Content-Type=application/x-www-form-urlencoded", "Accept=application/json"})
    public String registerEE(@RequestParam("email") String email,
                             @RequestParam("internalProductName") String internalProductName,
                             @RequestParam("name") String name,
                             @RequestParam("quantity") int quantity,
                             @RequestParam("referrer") String referrer,
                             @RequestParam("reference") String reference,
                             @RequestParam("subscriptionReference") String subscriptionReference,
                             @RequestParam(value = "test", required = false) String test,
                             @RequestParam("security_request_hash") String security_request_hash) {
        String decryptReferrer = EnDecryptHelper.decryptTextWithEncodeFriendly(referrer);
        String[] arr = decryptReferrer.split(";");
        BillingSubscription subscription = new BillingSubscription();
        subscription.setEmail(email);
        subscription.setAccountid(Integer.parseInt(arr[0]));
        subscription.setName(name);
        subscription.setBillingid(Integer.parseInt(arr[1]));
        subscription.setSubreference(subscriptionReference);
        subscription.setSubscriptioncustomerurl("");
        subscription.setCreatedtime(new DateTime().toDate());
        subscription.setStatus("Active");
        subscriptionMapper.insert(subscription);
        tempVariables.put(subscriptionReference, reference);
        return "Ok";
    }

    @RequestMapping(path = "/activated", method = RequestMethod.POST, headers =
            {"Content-Type=application/x-www-form-urlencoded", "Accept=application/json"})
    public String activateSubscription(@RequestParam("SubscriptionReference") String subscriptionReference,
                                       @RequestParam("SubscriptionReferrer") String subscriptionReferrer,
                                       @RequestParam("NextPeriodDate") String nextPeriodDate,
                                       @RequestParam("ProductName") String productName,
                                       @RequestParam("TotalPrice") String totalPrice,
                                       @RequestParam("CustomerFullName") String customerFullName,
                                       @RequestParam("Email") String email,
                                       @RequestParam("CompanyName") String companyName,
                                       @RequestParam("Phone") String phone,
                                       @RequestParam("SubscriptionCustomerUrl") String subscriptionCustomerUrl) throws Exception {
        try {
            String decryptReferrer = EnDecryptHelper.decryptTextWithEncodeFriendly(subscriptionReferrer);
            String[] arr = decryptReferrer.split(";");
            Integer sAccountId = Integer.parseInt(arr[0]);
            BillingSubscriptionExample ex = new BillingSubscriptionExample();
            ex.createCriteria().andSubreferenceEqualTo(subscriptionReference).andAccountidEqualTo(sAccountId);
            List<BillingSubscription> subscriptions = subscriptionMapper.selectByExample(ex);
            if (subscriptions.size() == 1) {
                BillingSubscription subscription = subscriptions.get(0);
                BillingSubscriptionHistory subscriptionHistory = new BillingSubscriptionHistory();
                subscriptionHistory.setSubscriptionid(subscription.getId());
                String reference = tempVariables.get(subscriptionReference);
                if (reference == null) {
                    reference = UUID.randomUUID().toString() + new DateTime().millisOfSecond().get();
                } else {
                    tempVariables.remove(subscriptionReference);
                }
                subscriptionHistory.setOrderid(reference);
                subscriptionHistory.setCreatedtime(new DateTime().toDate());
                subscriptionHistory.setStatus("Success");
                subscriptionHistory.setExpireddate(dateFormatter.parseLocalDate(nextPeriodDate).toDate());
                subscriptionHistory.setProductname(productName);
                subscriptionHistory.setTotalprice(Double.parseDouble(totalPrice));
                subscriptionHistoryMapper.insert(subscriptionHistory);

                subscription.setCompany(companyName);
                subscription.setContactname(customerFullName);
                subscription.setPhone(phone);
                subscription.setSubscriptioncustomerurl(subscriptionCustomerUrl);

                subscriptionMapper.updateByPrimaryKey(subscription);
                Broadcaster.broadcast(new BroadcastMessage(subscription.getAccountid(), null, ""));

                BillingAccountExample accountEx = new BillingAccountExample();
                accountEx.createCriteria().andIdEqualTo(sAccountId);
                BillingAccount billingAccount = new BillingAccount();
                billingAccount.setStatus(AccountStatusConstants.ACTIVE);
                billingAccountMapper.updateByExampleSelective(billingAccount, accountEx);
            } else {
                LOG.error("Find subscription with id " + subscriptionReference + "in account " + sAccountId + " has count" +
                        subscriptions.size());
            }
            return "Ok";
        } catch (Exception e) {
            StringBuilder errorMsg = new StringBuilder();
            errorMsg.append("SubscriptionReference: ").append(subscriptionReference).append("\n");
            errorMsg.append("SubscriptionReferrer: ").append(subscriptionReferrer).append("\n");
            errorMsg.append("NextPeriodDate: ").append(nextPeriodDate).append("\n");
            errorMsg.append("ProductName: ").append(productName).append("\n");
            errorMsg.append("CompanyName: ").append(companyName).append("\n");
            errorMsg.append("Email: ").append(email).append("\n");
            LOG.error("Error in activate account " + errorMsg);
            throw new MyCollabException(e);
        }
    }

    @RequestMapping(path = "/rebill-completed", method = RequestMethod.POST, headers =
            {"Content-Type=application/x-www-form-urlencoded", "Accept=application/json"})
    public String reBillSubscription(@RequestParam("OrderId") String orderId,
                                     @RequestParam("OrderProductName") String orderProductName,
                                     @RequestParam(value = "CustomerName", required = false) String customerFullName,
                                     @RequestParam(value = "CustomerCompany", required = false) String customerCompany,
                                     @RequestParam("OrderReferrer") String orderReferrer,
                                     @RequestParam("NextPeriodDate") String nextPeriodDate,
                                     @RequestParam(value = "CustomerEmail", required = false) String email,
                                     @RequestParam("SubscriptionReference") String subscriptionReference,
                                     @RequestParam("OrderSubTotalUSD") String orderSubTotalUSD,
                                     @RequestParam("Status") String status) throws Exception {
        BillingSubscriptionExample ex = new BillingSubscriptionExample();
        ex.createCriteria().andSubreferenceEqualTo(subscriptionReference);
        List<BillingSubscription> billingSubscriptions = subscriptionMapper.selectByExample(ex);
        if (billingSubscriptions.size() == 1) {
            BillingSubscription billingSubscription = billingSubscriptions.get(0);
            BillingSubscriptionHistory subscriptionHistory = new BillingSubscriptionHistory();
            subscriptionHistory.setSubscriptionid(billingSubscription.getId());
            subscriptionHistory.setOrderid(orderId);
            subscriptionHistory.setCreatedtime(new DateTime().toDate());
            subscriptionHistory.setStatus("Success");
            subscriptionHistory.setExpireddate(dateFormatter.parseLocalDate(nextPeriodDate).toDate());
            subscriptionHistory.setProductname(orderProductName);
            subscriptionHistory.setTotalprice(Double.parseDouble(orderSubTotalUSD));
            subscriptionHistoryMapper.insert(subscriptionHistory);

            BillingAccountExample accountEx = new BillingAccountExample();
            accountEx.createCriteria().andIdEqualTo(billingSubscription.getAccountid());
            BillingAccount billingAccount = new BillingAccount();
            billingAccount.setStatus(AccountStatusConstants.ACTIVE);
            billingAccountMapper.updateByExampleSelective(billingAccount, accountEx);
        } else {
            LOG.error("Find subscription with id " + subscriptionReference + "in account has count" +
                    billingSubscriptions.size());
        }

        return "Ok";
    }

    @RequestMapping(path = "/subscription-failed", method = RequestMethod.POST, headers =
            {"Content-Type=application/x-www-form-urlencoded", "Accept=application/json"})
    public String subscriptionFailedNotification(@RequestParam("SubscriptionReference") String subscriptionReference,
                                                 @RequestParam("SubscriptionEndDate") String subscriptionEndDate) {
        BillingSubscriptionExample ex = new BillingSubscriptionExample();
        ex.createCriteria().andSubreferenceEqualTo(subscriptionReference);
        List<BillingSubscription> billingSubscriptions = subscriptionMapper.selectByExample(ex);
        if (billingSubscriptions.size() == 1) {
            BillingSubscription billingSubscription = billingSubscriptions.get(0);
            BillingSubscriptionHistory subscriptionHistory = new BillingSubscriptionHistory();
            subscriptionHistory.setSubscriptionid(billingSubscription.getId());
            subscriptionHistory.setOrderid("");
            subscriptionHistory.setCreatedtime(new DateTime().toDate());
            subscriptionHistory.setStatus("Failed");
            BillingSubscriptionHistory previousHistory = subscriptionMapperExt.getTheLastBillingSuccess(billingSubscription.getAccountid());
            if (previousHistory != null) {
                subscriptionHistory.setExpireddate(previousHistory.getExpireddate());
                subscriptionHistory.setProductname(previousHistory.getProductname());
            } else {
                subscriptionHistory.setExpireddate(new DateTime().toDate());
            }

            subscriptionHistory.setProductname("");
            subscriptionHistory.setTotalprice(-1d);
            subscriptionHistoryMapper.insert(subscriptionHistory);

            BillingAccountExample accountEx = new BillingAccountExample();
            accountEx.createCriteria().andIdEqualTo(billingSubscription.getAccountid());
            BillingAccount billingAccount = new BillingAccount();
            billingAccount.setStatus(AccountStatusConstants.INVALID);
            billingAccountMapper.updateByExampleSelective(billingAccount, accountEx);
        } else {
            LOG.error("Find subscription with id " + subscriptionReference + "in account has count" +
                    billingSubscriptions.size());
        }
        return "Ok";
    }

    @RequestMapping(path = "/deactivated", method = RequestMethod.POST, headers =
            {"Content-Type=application/x-www-form-urlencoded", "Accept=application/json"})
    public String subscriptionDeactivatedNotification(@RequestParam("SubscriptionCustomerUrl") String subscriptionCustomerUrl,
                                                      @RequestParam("SubscriptionEndDate") String subscriptionEndDate,
                                                      @RequestParam(value = "SubscriptionIsTest", required = false)
                                                              String subscriptionIsTest,
                                                      @RequestParam(value = "SubscriptionQuantity", required = false)
                                                              String subscriptionQuantity,
                                                      @RequestParam("SubscriptionReference") String subscriptionReference,
                                                      @RequestParam(value = "SubscriptionReferrer", required = false)
                                                              String subscriptionReferrer) {
        BillingSubscriptionExample ex = new BillingSubscriptionExample();
        ex.createCriteria().andSubreferenceEqualTo(subscriptionReference);
        List<BillingSubscription> billingSubscriptions = subscriptionMapper.selectByExample(ex);
        if (billingSubscriptions.size() == 1) {
            BillingSubscription subscription = billingSubscriptions.get(0);
            BillingAccountExample accountEx = new BillingAccountExample();
            accountEx.createCriteria().andIdEqualTo(subscription.getAccountid());
            BillingAccount billingAccount = new BillingAccount();
            billingAccount.setStatus(AccountStatusConstants.INVALID);
            billingAccountMapper.updateByExampleSelective(billingAccount, accountEx);
        } else {
            LOG.error("Find subscription with id " + subscriptionReference + "in account has count" +
                    billingSubscriptions.size());
        }
        return "Ok";
    }
}
