package com.esofthead.mycollab.rest.server.resource;

import com.esofthead.mycollab.common.domain.MailRecipientField;
import com.esofthead.mycollab.configuration.EnDecryptHelper;
import com.esofthead.mycollab.configuration.SiteConfiguration;
import com.esofthead.mycollab.core.MyCollabException;
import com.esofthead.mycollab.core.UserInvalidInputException;
import com.esofthead.mycollab.core.utils.DateTimeUtils;
import com.esofthead.mycollab.license.LicenseInfo;
import com.esofthead.mycollab.license.LicenseType;
import com.esofthead.mycollab.module.mail.FileEmailAttachmentSource;
import com.esofthead.mycollab.module.mail.service.ExtMailService;
import com.esofthead.mycollab.module.mail.service.IContentGenerator;
import com.esofthead.mycollab.ondemand.module.support.dao.ProEditionInfoMapper;
import com.esofthead.mycollab.ondemand.module.support.domain.ProEditionInfo;
import com.verhas.licensor.License;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.FileUtils;
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
import java.net.URLDecoder;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Date;
import java.util.Locale;
import java.util.Properties;

/**
 * @author MyCollab Ltd
 * @since 5.2.5
 */
@RestController
public class LicenseManagerController {
    private static Logger LOG = LoggerFactory.getLogger(LicenseManagerController.class);
    private static final String PRIVATE_KEY = "1fc98e7f9ddff531d66de12e7c2c31d1";

    @Autowired
    private ProEditionInfoMapper proEditionMapper;

    @Autowired
    private ExtMailService extMailService;

    @Autowired
    private IContentGenerator contentGenerator;

    @RequestMapping(path = "/register-ee", method = RequestMethod.POST, headers =
            {"Content-Type=application/x-www-form-urlencoded", "Accept=application/json"})
    public String registerEE(@RequestParam("company") String company, @RequestParam("email") String email,
                             @RequestParam("internalProductName") String internalProductName,
                             @RequestParam("name") String name, @RequestParam("quantity") int quantity,
                             @RequestParam("reference") String reference,
                             @RequestParam("test") String test, @RequestParam("security_request_hash") String security_request_hash) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        StringBuilder stringBuilder = new StringBuilder().append(URLDecoder.decode(company, "UTF-8")).append
                (URLDecoder.decode(email, "UTF-8")).append(URLDecoder.decode(internalProductName, "UTF-8"))
                .append(URLDecoder.decode(name, "UTF-8")).append(quantity).append(URLDecoder.decode(reference, "UTF-8")).append(PRIVATE_KEY);
        String msg = DigestUtils.md5Hex(stringBuilder.toString());

        if (!msg.equals(security_request_hash)) {
//            LOG.error(String.format("Invalid request - Company: %s, Email: %s, Product: %s", company, email, internalProductName));
//            throw new UserInvalidInputException("Invalid request");
        }
        Integer customerId = 0;
        LocalDateTime now = new LocalDateTime();

        ProEditionInfo proEditionInfo = new ProEditionInfo();
        proEditionInfo.setCompany(company);
        proEditionInfo.setEmail(email);
        proEditionInfo.setInternalproductname(internalProductName);
        proEditionInfo.setName(name);
        proEditionInfo.setQuantity(quantity);
        proEditionInfo.setReference(reference);
        proEditionInfo.setIssuedate(now.toDate());
        proEditionInfo.setType("New");
        customerId = proEditionMapper.insertAndReturnKey(proEditionInfo);

        LicenseInfo licenseInfo = new LicenseInfo();
        licenseInfo.setCustomerId(EnDecryptHelper.encryptText("" + customerId));
        licenseInfo.setLicenseType(LicenseType.PRO_AD);
        if ("Growing (For less than 10 users)".equals(internalProductName)) {
            licenseInfo.setMaxUsers(10);
        } else {
            licenseInfo.setMaxUsers(9999);
        }

        licenseInfo.setIssueDate(now.toDate());
        licenseInfo.setLicenseOrg(company);
        licenseInfo.setExpireDate(now.plusYears(1).toDate());
        String license = encode(licenseInfo);
        try {
            File tempFile = File.createTempFile("mycollab", "lic");
            FileUtils.write(tempFile, license, "UTF-8");
            contentGenerator.putVariable("name", name);
            contentGenerator.putVariable("productName", internalProductName);
            contentGenerator.putVariable("issueDate", now.toDate());
            contentGenerator.putVariable("receipt", reference);
            extMailService.sendHTMLMail(SiteConfiguration.getNotifyEmail(), SiteConfiguration.getDefaultSiteName(),
                    Arrays.asList(new MailRecipientField(email, name)), null, null, contentGenerator.parseString
                            ("MyCollab Order Receipt $receipt"),
                    contentGenerator.parseFile("mailLicenseInfo.html", Locale.US), Arrays.asList(new
                            FileEmailAttachmentSource(tempFile, "mycollab.lic")));
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
            InputStream privateStream = LicenseManagerController.class.getClassLoader().getResourceAsStream("secring.gpg");
            license.loadKey(privateStream, "MyCollab Ltd (The private key for MyCollab license) <hainguyen@mycollab.com>");
            return license.encodeLicense("HanLong1979");
        } catch (Exception e) {
            throw new MyCollabException("Can not generate license", e);
        }
    }

    private Properties decode(String encodeStr) {
        try {
            License license = new License();
            InputStream publicStream = LicenseManagerController.class.getClassLoader().getResourceAsStream("pubring.gpg");
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

    public static void main(String[] args) throws Exception {
//        LicenseInfo info = new LicenseInfo();
//        info.setCustomerId(EnDecryptHelper.encryptText("3"));
//        info.setLicenseType(LicenseType.PRO);
//        info.setExpireDate(new LocalDate().plusDays(356).toDate());
//        info.setIssueDate(new LocalDate().toDate());
//        info.setLicenseOrg("Exide Technologies SAS");
//        info.setMaxUsers(20);
//        LicenseManagerController generator = new LicenseManagerController();
//        String str = generator.encode(info);
//        System.out.println(str);
//        generator.decode(str);
        LicenseManagerController generator = new LicenseManagerController();
        String s = generator.registerEE("FooBar+Inc.", "vagif.samadoghlu%40example.com", "Growing+%28For+less+than+10+users%29",
                "%D0%92%D0%B0%D0%B3%D0%B8%D1%84+%D0%A1%D3%99%D0%BC%D3%99%D0%B4%D0%BE%D2%93%D0%BB%D1%83", 1, "TEST_REF", "true", "124b228b547d5ac9388bcfefef2f4d4e");
        System.out.println("A: " + s);
    }
}
