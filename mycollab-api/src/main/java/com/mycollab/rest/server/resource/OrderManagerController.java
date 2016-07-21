package com.mycollab.rest.server.resource;

import com.mycollab.common.domain.MailRecipientField;
import com.mycollab.configuration.EnDecryptHelper;
import com.mycollab.configuration.SiteConfiguration;
import com.mycollab.core.MyCollabException;
import com.mycollab.core.utils.DateTimeUtils;
import com.mycollab.module.mail.FileAttachmentSource;
import com.mycollab.module.mail.UrlAttachmentSource;
import com.mycollab.module.mail.service.ExtMailService;
import com.mycollab.module.mail.service.IContentGenerator;
import com.mycollab.ondemand.module.billing.dao.ProEditionInfoMapper;
import com.mycollab.ondemand.module.billing.domain.ProEditionInfo;
import com.mycollab.pro.license.LicenseInfo;
import com.mycollab.pro.license.LicenseType;
import com.verhas.licensor.License;
import org.apache.commons.io.FileUtils;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.net.URL;
import java.util.Arrays;
import java.util.Properties;

/**
 * @author MyCollab Ltd
 * @since 5.2.5
 */
@RestController
@RequestMapping(path = "/order")
public class OrderManagerController {
    private static Logger LOG = LoggerFactory.getLogger(OrderManagerController.class);

    private static final String PRIVATE_KEY = "2d4966f695ceae18a67c0061112886c6";

    @Autowired
    private ProEditionInfoMapper proEditionMapper;

    @Autowired
    private ExtMailService extMailService;

    @Autowired
    private IContentGenerator contentGenerator;

    @RequestMapping(path = "/generatelicense", method = RequestMethod.POST, headers =
            {"Content-Type=application/x-www-form-urlencoded", "Accept=application/json"})
    public String generateLicense(@RequestParam("name") String name,
                                         @RequestParam("quantity") String quantity,
                                         @RequestParam("reference") String reference,
                                         @RequestParam("email") String email,
                                         @RequestParam(value = "company", required = false) String company,
                                         @RequestParam("referrer") String referrer,
                                         @RequestParam("internalProductName") String internalProductName,
                                         @RequestParam("tags") String tags,
                                         @RequestParam("tagValues") String tagValues,
                                         @RequestParam("sourceKey") String sourceKey,
                                         @RequestParam("sourceCampaign") String sourceCampaign,
                                         @RequestParam("test") String test,
                                         @RequestParam("subscriptionReference") String subscriptionReference) throws Exception {
//        LocalDateTime now = new LocalDateTime();
//
//        ProEditionInfo proEditionInfo = new ProEditionInfo();
//        proEditionInfo.setAddress1(addressStreet1);
//        proEditionInfo.setAddress2(addressStreet2);
//        proEditionInfo.setCity(addressCity);
//        proEditionInfo.setCountry(addressCountry);
//        proEditionInfo.setPhone(customerPhone);
//        proEditionInfo.setCost(Double.parseDouble(orderSubTotalUSD));
//        proEditionInfo.setCompany(customerCompany);
//        proEditionInfo.setEmail(customerEmail);
//        proEditionInfo.setInternalproductname(orderProductNames);
//        proEditionInfo.setName(customerName);
//        proEditionInfo.setQuantity(1);
//        proEditionInfo.setOrderid(orderId);
//        proEditionInfo.setIssuedate(now.toDate());
//        proEditionInfo.setType("New");
//        Integer customerId = proEditionMapper.insertAndReturnKey(proEditionInfo);
//
//        LicenseInfo licenseInfo = new LicenseInfo();
//        licenseInfo.setCustomerId(EnDecryptHelper.encryptText("" + customerId));
//        licenseInfo.setLicenseType(LicenseType.PRO);
//        if ("Growing (For less than 10 users)".equals(orderProductNames)) {
//            licenseInfo.setMaxUsers(10);
//        } else {
//            licenseInfo.setMaxUsers(9999);
//        }
//
//        licenseInfo.setIssueDate(now.toDate());
//        licenseInfo.setLicenseOrg(customerCompany);
//        licenseInfo.setExpireDate(now.plusYears(1).toDate());
//        return encode(licenseInfo);
        return "Ok";
    }

    @RequestMapping(path = "/completed", method = RequestMethod.POST, headers =
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
//        LocalDateTime now = new LocalDateTime();
//
//        ProEditionInfo proEditionInfo = new ProEditionInfo();
//        proEditionInfo.setAddress1(addressStreet1);
//        proEditionInfo.setAddress2(addressStreet2);
//        proEditionInfo.setCity(addressCity);
//        proEditionInfo.setCountry(addressCountry);
//        proEditionInfo.setPhone(customerPhone);
//        proEditionInfo.setCost(Double.parseDouble(orderSubTotalUSD));
//        proEditionInfo.setCompany(customerCompany);
//        proEditionInfo.setEmail(customerEmail);
//        proEditionInfo.setInternalproductname(orderProductNames);
//        proEditionInfo.setName(customerName);
//        proEditionInfo.setQuantity(1);
//        proEditionInfo.setOrderid(orderId);
//        proEditionInfo.setIssuedate(now.toDate());
//        proEditionInfo.setType("New");
//        Integer customerId = proEditionMapper.insertAndReturnKey(proEditionInfo);

//        LicenseInfo licenseInfo = new LicenseInfo();
//        licenseInfo.setCustomerId(EnDecryptHelper.encryptText("" + customerId));
//        licenseInfo.setLicenseType(LicenseType.PRO);
//        if ("Growing (For less than 10 users)".equals(orderProductNames)) {
//            licenseInfo.setMaxUsers(10);
//        } else {
//            licenseInfo.setMaxUsers(9999);
//        }
//
//        licenseInfo.setIssueDate(now.toDate());
//        licenseInfo.setLicenseOrg(customerCompany);
//        licenseInfo.setExpireDate(now.plusYears(1).toDate());
//        String license = encode(licenseInfo);
//        try {
//            File tempFile = File.createTempFile("mycollab", "lic");
//            FileUtils.write(tempFile, license, "UTF-8");
//            contentGenerator.putVariable("name", customerName);
//            contentGenerator.putVariable("productName", orderProductNames);
//            contentGenerator.putVariable("issueDate", now.toDate());
//            contentGenerator.putVariable("orderId", orderId);
//
//            extMailService.sendHTMLMail(SiteConfiguration.getNotifyEmail(), SiteConfiguration.getDefaultSiteName(),
//                    Arrays.asList(new MailRecipientField(customerEmail, customerName)), null, null, String.format("MyCollab " +
//                            "Order Receipt %s", orderId),
//                    contentGenerator.parseFile("mailLicenseInfo.ftl"), Arrays.asList(new
//                            FileAttachmentSource("mycollab.lic", tempFile), new UrlAttachmentSource("Invoice.pdf", new
//                            URL("https://sites.fastspring.com/mycollab/order/invoice/" + orderId + "/pdf"))));
//            tempFile.delete();
//        } catch (Exception e) {
//            LOG.error("Error to generate the license", e);
//        }
        return "Ok";
    }

    @RequestMapping(path = "/register-trial", method = RequestMethod.POST, headers =
            {"Content-Type=application/x-www-form-urlencoded", "Accept=application/json"})
    public String registerTrial() {
        LicenseInfo info = new LicenseInfo();
        info.setCustomerId("0");
        info.setLicenseType(LicenseType.PRO_TRIAL);
        info.setExpireDate(new LocalDate().plusDays(30).toDate());
        info.setIssueDate(new LocalDate().toDate());
        info.setLicenseOrg("MyCollab");
        info.setMaxUsers(30);
        return encode(info);
    }

    private static String encode(LicenseInfo licenseInfo) {
        try {
            Properties prop = new Properties();
            prop.setProperty("customerId", licenseInfo.getCustomerId());
            prop.setProperty("licenseType", licenseInfo.getLicenseType().name());
            prop.setProperty("licenseOrg", licenseInfo.getLicenseOrg());
            prop.setProperty("expireDate", DateTimeUtils.formatDateToW3C(licenseInfo.getExpireDate()));
            prop.setProperty("issueDate", DateTimeUtils.formatDateToW3C(licenseInfo.getIssueDate()));
            prop.setProperty("maxUsers", licenseInfo.getMaxUsers() + "");
            StringWriter outStream = new StringWriter();
            prop.store(outStream, "");
            String licenseStrInfo = outStream.toString();

            License license = new License();
            license.setLicense(licenseStrInfo);
            InputStream privateStream = OrderManagerController.class.getClassLoader().getResourceAsStream("secring.gpg");
            license.loadKey(privateStream, "MyCollab Ltd (The private key for MyCollab license) <hainguyen@mycollab.com>");
            return license.encodeLicense("HanLong1979");
        } catch (Exception e) {
            throw new MyCollabException("Can not generate license", e);
        }
    }

    private static Properties decode(String encodeStr) {
        try {
            License license = new License();
            InputStream publicStream = OrderManagerController.class.getClassLoader().getResourceAsStream("pubring.gpg");
            license.loadKeyRing(publicStream, null);
            license.setLicenseEncoded(encodeStr);
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            license.dumpLicense(outputStream);
            outputStream.flush();
            Properties prop = new Properties();
            prop.load(new ByteArrayInputStream(outputStream.toByteArray()));
            return prop;
        } catch (Exception e) {
            throw new MyCollabException("Failed to check MyCollab license", e);
        }
    }

    public static void main(String[] args) {
        LicenseInfo licenseInfo = new LicenseInfo();
        licenseInfo.setCustomerId("0");
        licenseInfo.setExpireDate(new DateTime().minusDays(1).toDate());
        licenseInfo.setIssueDate(new DateTime().minusDays(30).toDate());
        licenseInfo.setMaxUsers(10);
        licenseInfo.setLicenseType(LicenseType.PRO_TRIAL);
        licenseInfo.setLicenseOrg("Org");
        System.out.println(encode(licenseInfo));
    }
}
