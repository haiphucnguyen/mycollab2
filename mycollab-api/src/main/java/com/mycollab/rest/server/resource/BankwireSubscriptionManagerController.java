package com.mycollab.rest.server.resource;

import com.mycollab.configuration.EnDecryptHelper;
import com.mycollab.module.billing.AccountStatusConstants;
import com.mycollab.module.user.dao.BillingAccountMapper;
import com.mycollab.module.user.domain.BillingAccount;
import com.mycollab.module.user.domain.BillingAccountExample;
import com.mycollab.ondemand.module.billing.dao.BillingSubscriptionHistoryMapper;
import com.mycollab.ondemand.module.billing.dao.BillingSubscriptionMapper;
import com.mycollab.ondemand.module.billing.domain.BillingSubscription;
import com.mycollab.ondemand.module.billing.domain.BillingSubscriptionHistory;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author MyCollab Ltd
 * @since 5.4.2
 */
@RestController
@RequestMapping(path = "/subscription")
public class BankwireSubscriptionManagerController {
    @Autowired
    private BillingSubscriptionMapper subscriptionMapper;

    @Autowired
    private BillingSubscriptionHistoryMapper subscriptionHistoryMapper;

    @Autowired
    private BillingAccountMapper billingAccountMapper;

    @RequestMapping(path = "/bankwireMethod", method = RequestMethod.POST, headers =
            {"Content-Type=application/x-www-form-urlencoded", "Accept=application/json"})
    public String orderCompletedCallback(@RequestParam("AddressCity") String addressCity,
                                         @RequestParam("AddressCountry") String addressCountry,
                                         @RequestParam("AddressPostalCode") String addressPostalCode,
                                         @RequestParam("AddressStreet1") String addressStreet1,
                                         @RequestParam("AddressStreet2") String addressStreet2,
                                         @RequestParam("CustomerCompany") String customerCompany,
                                         @RequestParam("CustomerEmail") String customerEmail,
                                         @RequestParam("CustomerName") String customerName,
                                         @RequestParam("CustomerPhone") String customerPhone,
                                         @RequestParam("OrderID") String orderId,
                                         @RequestParam("OrderProductNames") String orderProductNames,
                                         @RequestParam("OrderReferrer") String orderReferrer,
                                         @RequestParam("OrderSubTotalUSD") String orderSubTotalUSD) throws Exception {
        String decryptReferrer = EnDecryptHelper.decryptTextWithEncodeFriendly(orderReferrer);
        String[] arr = decryptReferrer.split(";");
        BillingSubscription subscription = new BillingSubscription();
        subscription.setEmail(customerEmail);
        subscription.setAccountid(Integer.parseInt(arr[0]));
        subscription.setName(orderProductNames);
        subscription.setBillingid(Integer.parseInt(arr[1]));
        subscription.setSubreference(orderId);
        subscription.setSubscriptioncustomerurl("");
        subscription.setCreatedtime(new DateTime().toDate());
        subscription.setStatus("Active");
        subscription.setCompany(customerCompany);
        subscription.setContactname(customerName);
        subscription.setPhone(customerPhone);
        subscription.setSubscriptioncustomerurl("");
        subscriptionMapper.insertAndReturnKey(subscription);

        BillingSubscriptionHistory subscriptionHistory = new BillingSubscriptionHistory();
        subscriptionHistory.setSubscriptionid(subscription.getId());
        subscriptionHistory.setOrderid(orderId);
        subscriptionHistory.setCreatedtime(new DateTime().toDate());
        subscriptionHistory.setStatus("Success");
        subscriptionHistory.setExpireddate(new DateTime().plusYears(1).toDate());
        subscriptionHistory.setProductname(orderProductNames);
        subscriptionHistory.setTotalprice(Double.parseDouble(orderSubTotalUSD));
        subscriptionHistoryMapper.insert(subscriptionHistory);

        BillingAccountExample accountEx = new BillingAccountExample();
        accountEx.createCriteria().andIdEqualTo(Integer.parseInt(arr[0]));
        BillingAccount billingAccount = new BillingAccount();
        billingAccount.setStatus(AccountStatusConstants.ACTIVE);
        billingAccount.setPaymentmethod("Bankwire");
        billingAccountMapper.updateByExampleSelective(billingAccount, accountEx);

        return "Ok";
    }
}
