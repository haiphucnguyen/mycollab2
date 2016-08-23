package com.mycollab.rest.server.resource;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Properties;

/**
 * @author MyCollab Ltd
 * @since 5.4.2
 */
@RestController
@RequestMapping(path = "/subscription")
public class BankwireSubscriptionManagerController {

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
        return "Ok";
    }
}
