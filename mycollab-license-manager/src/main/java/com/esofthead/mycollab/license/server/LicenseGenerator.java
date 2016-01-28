package com.esofthead.mycollab.license.server;

import com.esofthead.mycollab.core.MyCollabException;
import com.esofthead.mycollab.core.utils.DateTimeUtils;
import com.esofthead.mycollab.license.LicenseInfo;
import com.esofthead.mycollab.license.LicenseType;
import com.verhas.licensor.License;
import org.joda.time.LocalDate;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.Properties;

/**
 * @author MyCollab Ltd
 * @since 5.2.6
 */
public class LicenseGenerator {
    public String encode(LicenseInfo licenseInfo) {
        try {
            Properties prop = new Properties();
            prop.setProperty("customerId", licenseInfo.getCustomerId());
            prop.setProperty("licenseOrg", licenseInfo.getLicenseOrg());
            prop.setProperty("expireDate", DateTimeUtils.formatDateToW3C(licenseInfo.getExpireDate()));
            prop.setProperty("issueDate", DateTimeUtils.formatDateToW3C(licenseInfo.getIssueDate()));
            prop.setProperty("licenseType", licenseInfo.getLicenseType().name());
            StringWriter outStream = new StringWriter();
            prop.store(outStream, "");
            String licenseStrInfo = outStream.toString();

            License license = new License();
            license.setLicense(licenseStrInfo);
            InputStream privateStream = LicenseGenerator.class.getClassLoader().getResourceAsStream("secring.gpg");
            license.loadKey(privateStream, "MyCollab Ltd (The private key for MyCollab license) <hainguyen@mycollab.com>");
            return license.encodeLicense("HanLong1979");
        } catch (Exception e) {
            throw new MyCollabException("Can not generate license", e);
        }
    }

    public Properties decode(String encodeStr) {
        try {
            License license = new License();
            InputStream publicStream = LicenseGenerator.class.getClassLoader().getResourceAsStream("pubring.gpg");
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
        LicenseInfo info = new LicenseInfo();
        info.setCustomerId("1");
        info.setLicenseType(LicenseType.PRO_TRIAL);
        info.setExpireDate(new LocalDate().plusDays(10).toDate());
        info.setIssueDate(new LocalDate().minusDays(30).toDate());
        info.setLicenseOrg("eSoftHead");
        info.setMaxUsers(10);
        LicenseGenerator generator = new LicenseGenerator();
        String str = generator.encode(info);
        System.out.println(str);
        generator.decode(str);
    }
}
