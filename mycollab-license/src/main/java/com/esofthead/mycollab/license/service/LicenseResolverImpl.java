package com.esofthead.mycollab.license.service;

import com.esofthead.mycollab.core.utils.DateTimeUtils;
import com.esofthead.mycollab.core.utils.FileUtils;
import com.esofthead.mycollab.license.LicenseInfo;
import com.esofthead.mycollab.license.LicenseResolver;
import com.verhas.licensor.License;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author MyCollab Ltd
 * @since 5.2.6
 */
@Service
public class LicenseResolverImpl implements LicenseResolver, InitializingBean {
    private LicenseInfo licenseInfo = null;

    @Override
    public void afterPropertiesSet() throws Exception {
        String userFolder = System.getProperty("user.home");
        File homeDir = new File(userFolder + "/.mycollab");
        FileUtils.mkdirs(homeDir);
        File licenseFile = new File(homeDir, "license.lic");
        if (licenseFile.isFile() && licenseFile.exists()) {
            License license = new License();
            InputStream publicStream = LicenseResolverImpl.class.getClassLoader().getResourceAsStream("pubring.gpg");
            license.loadKeyRing(publicStream, null);
            license.setLicenseEncoded(licenseFile, "UTF-8");
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            license.dumpLicense(outputStream);
            outputStream.flush();
            Properties prop = new Properties();
            prop.load(new ByteArrayInputStream(outputStream.toByteArray()));
            licenseInfo = new LicenseInfo();
            licenseInfo.setCustomerId(prop.getProperty("customerId"));
            licenseInfo.setEdition(prop.getProperty("edition"));
            licenseInfo.setExpireDate(DateTimeUtils.parseDateByW3C(prop.getProperty("expireDate")));
            licenseInfo.setIssueDate(DateTimeUtils.parseDateByW3C(prop.getProperty("issueDate")));
            licenseInfo.setLicenseOrg(prop.getProperty("licenseOrg"));
        }
    }

    @Override
    public LicenseInfo getLicenseInfo() {
        return licenseInfo;
    }
}
