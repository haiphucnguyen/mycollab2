package com.mycollab.rest.server.resource;

import com.google.common.base.MoreObjects;
import com.mycollab.configuration.EnDecryptHelper;
import com.mycollab.core.MyCollabException;
import com.mycollab.core.utils.DateTimeUtils;
import com.mycollab.module.mail.service.ExtMailService;
import com.mycollab.module.mail.service.IContentGenerator;
import com.mycollab.ondemand.module.billing.dao.ProEditionInfoMapper;
import com.mycollab.ondemand.module.billing.domain.ProEditionInfo;
import com.mycollab.ondemand.module.billing.domain.ProEditionInfoExample;
import com.mycollab.pro.license.LicenseInfo;
import com.mycollab.pro.license.LicenseType;
import com.verhas.licensor.License;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.List;
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

    @PostMapping(path = "/generatelicense", headers =
            {"Content-Type=application/x-www-form-urlencoded", "Accept=application/json"})
    public String generateLicense(@RequestParam("name") String name,
                                  @RequestParam("quantity") String quantity,
                                  @RequestParam("reference") String reference,
                                  @RequestParam("email") String email,
                                  @RequestParam(value = "company", required = false) String company,
                                  @RequestParam(value = "referrer", required = false) String referrer,
                                  @RequestParam("internalProductName") String internalProductName,
                                  @RequestParam(value = "tags", required = false) String tags,
                                  @RequestParam(value = "tagValues", required = false) String tagValues,
                                  @RequestParam(value = "sourceKey", required = false) String sourceKey,
                                  @RequestParam(value = "sourceCampaign", required = false) String sourceCampaign,
                                  @RequestParam(value = "test", required = false) String test,
                                  @RequestParam(value = "subscriptionReference", required = false) String subscriptionReference)
            throws Exception {
        LocalDateTime now = new LocalDateTime();

        ProEditionInfo proEditionInfo = new ProEditionInfo();
        proEditionInfo.setInternalproductname(internalProductName);
        proEditionInfo.setEmail(email);
        proEditionInfo.setName(name);
        proEditionInfo.setQuantity(1);
        proEditionInfo.setOrderid(reference);
        proEditionInfo.setIssuedate(now.toDate());
        proEditionInfo.setType("New");
        Integer customerId = proEditionMapper.insertAndReturnKey(proEditionInfo);

        LicenseInfo licenseInfo = new LicenseInfo();
        licenseInfo.setCustomerId(EnDecryptHelper.encryptText("" + customerId));
        licenseInfo.setLicenseType(LicenseType.PRO);
        if ("Growing (For less than 10 users)".equals(internalProductName)) {
            licenseInfo.setMaxUsers(10);
        } else {
            licenseInfo.setMaxUsers(9999);
        }

        licenseInfo.setIssueDate(now.toDate());
        licenseInfo.setLicenseOrg(MoreObjects.firstNonNull(company, "Default"));
        licenseInfo.setExpireDate(now.plusYears(1).toDate());
        String license = encode(licenseInfo);
        StringBuilder result = new StringBuilder();
        result.append("Mime-Version: 1.0\n")
                .append("Content-Type: multipart/mixed; boundary=license\n\n\n")
                .append("--license\n")
                .append("Content-Disposition: inline; filename=mycollab.lic\n")
                .append("Content-Type: text/plain\n")
                .append("Content-Transfer-Encoding: 8BIT\n\n")
                .append(license)
                .append("\n--license--");
        return result.toString();
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
        ProEditionInfoExample ex = new ProEditionInfoExample();
        ex.createCriteria().andOrderidEqualTo(orderId);
        List<ProEditionInfo> proEditionInfos = proEditionMapper.selectByExample(ex);
        if (proEditionInfos.size() == 1) {
            ProEditionInfo proEditionInfo = proEditionInfos.get(0);
            proEditionInfo.setAddress1(addressStreet1);
            proEditionInfo.setAddress2(addressStreet2);
            proEditionInfo.setCity(addressCity);
            proEditionInfo.setCountry(addressCountry);
            proEditionInfo.setPhone(customerPhone);
            proEditionInfo.setCost(Double.parseDouble(orderSubTotalUSD));
            proEditionInfo.setCompany(customerCompany);
            proEditionInfo.setType("New");
            proEditionMapper.updateByPrimaryKeySelective(proEditionInfo);
        } else {
            LOG.error("There is invalid order " + orderId);
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
