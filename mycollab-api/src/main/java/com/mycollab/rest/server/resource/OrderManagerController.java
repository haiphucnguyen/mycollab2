package com.mycollab.rest.server.resource;

import com.mycollab.common.domain.MailRecipientField;
import com.mycollab.configuration.EnDecryptHelper;
import com.mycollab.configuration.SiteConfiguration;
import com.mycollab.core.MyCollabException;
import com.mycollab.core.utils.DateTimeUtils;
import com.mycollab.module.mail.FileEmailAttachmentSource;
import com.mycollab.module.mail.service.ExtMailService;
import com.mycollab.module.mail.service.IContentGenerator;
import com.mycollab.ondemand.module.billing.dao.ProEditionInfoMapper;
import com.mycollab.ondemand.module.billing.domain.ProEditionInfo;
import com.mycollab.pro.license.LicenseInfo;
import com.mycollab.pro.license.LicenseType;
import com.mycollab.reporting.ReportStyles;
import com.verhas.licensor.License;
import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.builder.component.ComponentBuilder;
import net.sf.dynamicreports.report.builder.component.HorizontalListBuilder;
import net.sf.dynamicreports.report.builder.component.VerticalListBuilder;
import net.sf.dynamicreports.report.builder.style.StyleBuilder;
import net.sf.dynamicreports.report.constant.HorizontalTextAlignment;
import net.sf.dynamicreports.report.constant.Markup;
import net.sf.dynamicreports.report.constant.PageType;
import org.apache.commons.io.FileUtils;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.awt.*;
import java.io.*;
import java.util.Arrays;
import java.util.Properties;

import static net.sf.dynamicreports.report.builder.DynamicReports.*;

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
        LocalDateTime now = new LocalDateTime();

        ProEditionInfo proEditionInfo = new ProEditionInfo();
        proEditionInfo.setAddress1(addressStreet1);
        proEditionInfo.setAddress2(addressStreet2);
        proEditionInfo.setCity(addressCity);
        proEditionInfo.setCountry(addressCountry);
        proEditionInfo.setPhone(customerPhone);
        proEditionInfo.setCost(Double.parseDouble(orderSubTotalUSD));
        proEditionInfo.setCompany(customerCompany);
        proEditionInfo.setEmail(customerEmail);
        proEditionInfo.setInternalproductname(orderProductNames);
        proEditionInfo.setName(customerName);
        proEditionInfo.setQuantity(1);
        proEditionInfo.setOrderid(orderId);
        proEditionInfo.setIssuedate(now.toDate());
        proEditionInfo.setType("New");
        Integer customerId = proEditionMapper.insertAndReturnKey(proEditionInfo);

        LicenseInfo licenseInfo = new LicenseInfo();
        licenseInfo.setCustomerId(EnDecryptHelper.encryptText("" + customerId));
        licenseInfo.setLicenseType(LicenseType.PRO);
        if ("Growing (For less than 10 users)".equals(orderProductNames)) {
            licenseInfo.setMaxUsers(10);
        } else {
            licenseInfo.setMaxUsers(9999);
        }

        licenseInfo.setIssueDate(now.toDate());
        licenseInfo.setLicenseOrg(customerCompany);
        licenseInfo.setExpireDate(now.plusYears(1).toDate());
        String license = encode(licenseInfo);
        try {
            File tempFile = File.createTempFile("mycollab", "lic");
            FileUtils.write(tempFile, license, "UTF-8");
            contentGenerator.putVariable("name", customerName);
            contentGenerator.putVariable("productName", orderProductNames);
            contentGenerator.putVariable("issueDate", now.toDate());
            contentGenerator.putVariable("receipt", orderId);

            File receiptReport = receiptReport(orderId, customerCompany, licenseInfo.getCustomerId(), Double.parseDouble(orderSubTotalUSD));
            extMailService.sendHTMLMail(SiteConfiguration.getNotifyEmail(), SiteConfiguration.getDefaultSiteName(),
                    Arrays.asList(new MailRecipientField(customerEmail, customerName)), null, null, String.format("MyCollab " +
                            "Order Receipt %s", orderId),
                    contentGenerator.parseFile("mailLicenseInfo.ftl"), Arrays.asList(new
                            FileEmailAttachmentSource(tempFile, "mycollab.lic"), new FileEmailAttachmentSource
                            (receiptReport, "Receipt-" + orderId + ".pdf")));
            tempFile.delete();
        } catch (Exception e) {
            LOG.error("Error to generate the license", e);
        }
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

    private String encode(LicenseInfo licenseInfo) {
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

    private Properties decode(String encodeStr) {
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

    private File receiptReport(String reference, String company, String customerId, Double cost) throws Exception {
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
                                cmp.text("Company: " + company),
                                cmp.text("Customer ID: " + customerId)))
        );

        HorizontalListBuilder add = cmp.horizontalList().add(dynamicReportsComponent).newRow().add(cmp.verticalGap(15));
        report.title(add);

        VerticalListBuilder summaryComp = cmp.verticalList()
                .add(cmp.horizontalList().add(cmp.text("MyCollab Ultimate Edition for 1 year").setStyle(reportStyles.getH3Style()),
                        cmp.text(cost + " USD").setStyle(reportStyles.getH3Style()))
                        .setStyle(stl.style().setBackgroundColor(new Color(235, 184, 132))))
                .add(cmp.horizontalList().add(cmp.text("Number of servers").setStyle(reportStyles.getH3Style()),
                        cmp.text("1").setStyle(reportStyles.getH3Style()))
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
