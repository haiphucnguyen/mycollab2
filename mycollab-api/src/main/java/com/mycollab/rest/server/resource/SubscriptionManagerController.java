package com.mycollab.rest.server.resource;

import com.mycollab.common.domain.MailRecipientField;
import com.mycollab.configuration.EnDecryptHelper;
import com.mycollab.configuration.SiteConfiguration;
import com.mycollab.core.BroadcastMessage;
import com.mycollab.core.Broadcaster;
import com.mycollab.module.billing.AccountStatusConstants;
import com.mycollab.module.mail.FileEmailAttachmentSource;
import com.mycollab.module.mail.service.ExtMailService;
import com.mycollab.module.mail.service.IContentGenerator;
import com.mycollab.module.user.dao.BillingAccountMapper;
import com.mycollab.module.user.domain.BillingAccount;
import com.mycollab.module.user.domain.BillingAccountExample;
import com.mycollab.ondemand.module.billing.dao.BillingSubscriptionHistoryMapper;
import com.mycollab.ondemand.module.billing.dao.BillingSubscriptionMapper;
import com.mycollab.ondemand.module.billing.domain.BillingSubscription;
import com.mycollab.ondemand.module.billing.domain.BillingSubscriptionExample;
import com.mycollab.ondemand.module.billing.domain.BillingSubscriptionHistory;
import com.mycollab.reporting.ReportStyles;
import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.builder.component.ComponentBuilder;
import net.sf.dynamicreports.report.builder.component.HorizontalListBuilder;
import net.sf.dynamicreports.report.builder.component.VerticalListBuilder;
import net.sf.dynamicreports.report.builder.style.StyleBuilder;
import net.sf.dynamicreports.report.constant.HorizontalTextAlignment;
import net.sf.dynamicreports.report.constant.Markup;
import net.sf.dynamicreports.report.constant.PageType;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.util.*;
import java.util.List;

import static net.sf.dynamicreports.report.builder.DynamicReports.*;

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
    private BillingSubscriptionHistoryMapper subscriptionHistoryMapper;

    @Autowired
    private BillingAccountMapper billingAccountMapper;

    @Autowired
    private ExtMailService extMailService;

    @Autowired
    private IContentGenerator contentGenerator;

    @RequestMapping(path = "/register", method = RequestMethod.POST, headers =
            {"Content-Type=application/x-www-form-urlencoded", "Accept=application/json"})
    public String registerEE(@RequestParam("email") String email,
                             @RequestParam("internalProductName") String internalProductName,
                             @RequestParam("name") String name,
                             @RequestParam("quantity") int quantity,
                             @RequestParam("referrer") String referrer,
                             @RequestParam("reference") String reference,
                             @RequestParam("subscriptionReference") String subscriptionReference,
                             @RequestParam("billingPlanId") String billingPlanId,
                             @RequestParam("test") String test,
                             @RequestParam("security_request_hash") String security_request_hash) {
        Integer sAccountId = Integer.parseInt(EnDecryptHelper.decryptText(referrer));
        BillingSubscription subscription = new BillingSubscription();
        subscription.setEmail(email);
        subscription.setAccountid(sAccountId);
        subscription.setName(name);
        subscription.setBillingid(Integer.parseInt(billingPlanId));
        subscription.setSubreference(subscriptionReference);
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
                                       @RequestParam("Phone") String phone) throws Exception {
        BillingSubscriptionExample ex = new BillingSubscriptionExample();
        Integer sAccountId = 0;
        try {
            LOG.info("Referrer: " + subscriptionReferrer);
            sAccountId = Integer.parseInt(EnDecryptHelper.decryptText(subscriptionReferrer));
        } catch (Exception e) {
            LOG.error("Referrer is invalid " + subscriptionReferrer);
            return "Failed";
        }
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

            subscriptionMapper.updateByPrimaryKey(subscription);
            Broadcaster.broadcast(new BroadcastMessage(subscription.getAccountid(), null, ""));

            BillingAccountExample accountEx = new BillingAccountExample();
            accountEx.createCriteria().andIdEqualTo(sAccountId);
            BillingAccount billingAccount = new BillingAccount();
            billingAccount.setStatus(AccountStatusConstants.ACTIVE);
            billingAccountMapper.updateByExampleSelective(billingAccount, accountEx);

            contentGenerator.putVariable("customerName", customerFullName);
            contentGenerator.putVariable("nextPaymentDate", nextPeriodDate);
            File receiptReport = receiptReport(subscriptionReference, reference, productName, email, customerFullName,
                    companyName, Double.parseDouble(totalPrice));
            extMailService.sendHTMLMail(SiteConfiguration.getNotifyEmail(), SiteConfiguration.getDefaultSiteName(),
                    Arrays.asList(new MailRecipientField(email, customerFullName)), null, null, String.format("[%s] " +
                            "Payment charged successfully", SiteConfiguration.getDefaultSiteName()),
                    contentGenerator.parseFile("paymentChargedSuccessfully.ftl"), Arrays.asList(
                            new FileEmailAttachmentSource
                                    (receiptReport, "Receipt-" + subscriptionReference + ".pdf")));


        } else {
            LOG.error("Find subscription with id " + subscriptionReference + "in account " + sAccountId + " has count" +
                    subscriptions.size());
        }
        return "Ok";
    }

    @RequestMapping(path = "/rebill-completed", method = RequestMethod.POST, headers =
            {"Content-Type=application/x-www-form-urlencoded", "Accept=application/json"})
    public String reBillSubscription(@RequestParam("OrderId") String orderId,
                                     @RequestParam("OrderProductName") String orderProductName,
                                     @RequestParam("CustomerName") String customerFullName,
                                     @RequestParam("CustomerCompany") String customerCompany,
                                     @RequestParam("OrderReferrer") String orderReferrer,
                                     @RequestParam("NextPeriodDate") String nextPeriodDate,
                                     @RequestParam("CustomerEmail") String email,
                                     @RequestParam("SubscriptionReference") String subscriptionReference,
                                     @RequestParam("OrderSubTotalUSD") String orderSubTotalUSD,
                                     @RequestParam("Status") String status) throws Exception {
        LOG.info("Referrer: " + orderReferrer);
        contentGenerator.putVariable("customerName", customerFullName);
        contentGenerator.putVariable("nextPaymentDate", nextPeriodDate);
        File receiptReport = receiptReport(subscriptionReference, orderId, orderProductName, email, customerFullName,
                customerCompany, Double.parseDouble(orderSubTotalUSD));
        extMailService.sendHTMLMail(SiteConfiguration.getNotifyEmail(), SiteConfiguration.getDefaultSiteName(),
                Arrays.asList(new MailRecipientField(email, customerFullName)), null, null, String.format("[%s] " +
                        "Payment charged successfully", SiteConfiguration.getDefaultSiteName()),
                contentGenerator.parseFile("paymentChargedSuccessfully.ftl"), Arrays.asList(
                        new FileEmailAttachmentSource
                                (receiptReport, "Receipt-" + subscriptionReference + ".pdf")));
        return "Ok";
    }

    @RequestMapping(path = "/subscription-failed", method = RequestMethod.POST, headers =
            {"Content-Type=application/x-www-form-urlencoded", "Accept=application/json"})
    public String returnCompletedSubscription(@RequestParam("SubscriptionReference") String subscriptionReference) {

        return "Ok";
    }


    private File receiptReport(String subscriptionReference, String reference, String productName, String email, String customerFullName,
                               String customerCompany, Double price) throws Exception {
        File referenceFile = File.createTempFile("mycollab", "pdf");
        ReportStyles reportStyles = ReportStyles.instance();
        JasperReportBuilder report = report().setPageFormat(PageType.A3);
        StyleBuilder style = stl.style().setMarkup(Markup.STYLED);
        ComponentBuilder<?, ?> dynamicReportsComponent = cmp.verticalList(
                cmp.image(getClass().getClassLoader().getResourceAsStream("images/logo.png")).setFixedDimension(150, 28),
                cmp.text("MyCollab LLC").setStyle(reportStyles.getH2Style()),
                cmp.horizontalList().newRow(15).add(reportStyles.line()).newRow(15),
                cmp.horizontalList().add(cmp.verticalList(cmp.text("79/11 Tran Huy Lieu, 12th Ward,"),
                        cmp.text("Phu Nhuan District, HCM city, Viet Nam"),
                        cmp.text("Web: <a href=\"https://www.mycollab.com\"><u>https://www.mycollab.com</u></a>").setStyle(style),
                        cmp.text("Email: <a href=\"mailto:support@mycollab.com\"><u>support@mycollab.com</u></a>").setStyle(style)))
                        .add(cmp.verticalList(cmp.text("Receipt no: " + reference),
                                cmp.text("Receipt date: " + DateTimeFormat.forPattern("E, dd MMM yyyy").print(new LocalDate())),
                                cmp.text("Company: " + customerCompany),
                                cmp.text("Subscription Reference ID: " + subscriptionReference)))
        );

        HorizontalListBuilder add = cmp.horizontalList().add(dynamicReportsComponent).newRow().add(cmp.verticalGap(15));
        report.title(add);

        VerticalListBuilder summaryComp = cmp.verticalList()
                .add(cmp.horizontalList().add(cmp.text("Service subscription: " + productName).setStyle(reportStyles.getH3Style()),
                        cmp.text(price + " USD").setStyle(reportStyles.getH3Style()))
                        .setStyle(stl.style().setBackgroundColor(new Color(235, 184, 132))))
                .add(cmp.horizontalList().newRow(15))
                .add(cmp.text("All prices are in USD. If you have any questions concerning this receipt, contact us at"),
                        cmp.text("Billing Support").setStyle(reportStyles.getUnderlineStyle())
                                .setHyperLink(hyperLink("mailto:support@mycollab.com")).setHorizontalTextAlignment(HorizontalTextAlignment.CENTER),
                        cmp.text("We thank you for your business").setStyle(stl.style().setFontSize(14)
                                .setForegroundColor(new Color(221, 133, 44)).setHorizontalTextAlignment(HorizontalTextAlignment.CENTER)));
        report.summary(summaryComp);

        report.toPdf(new FileOutputStream(referenceFile));
        return referenceFile;
    }
}
