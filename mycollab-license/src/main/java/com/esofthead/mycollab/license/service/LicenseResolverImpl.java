package com.esofthead.mycollab.license.service;

import com.esofthead.mycollab.core.MyCollabException;
import com.esofthead.mycollab.core.utils.DateTimeUtils;
import com.esofthead.mycollab.core.utils.FileUtils;
import com.esofthead.mycollab.license.LicenseInfo;
import com.esofthead.mycollab.license.LicenseResolver;
import com.esofthead.mycollab.license.LicenseType;
import com.verhas.licensor.License;
import org.bouncycastle.openpgp.PGPException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.Properties;

/**
 * @author MyCollab Ltd
 * @since 5.2.6
 */
@Service
public class LicenseResolverImpl implements LicenseResolver, InitializingBean {
    private static final Logger LOG = LoggerFactory.getLogger(LicenseResolverImpl.class);

    private LicenseInfo licenseInfo = null;

    @Override
    public void afterPropertiesSet() throws Exception {
        File licenseFile = getLicenseFile();
        if (licenseFile.isFile() && licenseFile.exists()) {
            byte[] licenseBytes = org.apache.commons.io.FileUtils.readFileToByteArray(licenseFile);
            checkLicenseInfo(licenseBytes, false);
        }
    }

    private File getLicenseFile() {
        File homeDir = FileUtils.getHomeFolder();
        return new File(homeDir, "mycollab.lic");
    }

    @Override
    public void checkAndSaveLicenseInfo(String licenseInputText) {
        try {
            checkLicenseInfo(licenseInputText.getBytes("UTF-8"), true);
        } catch (UnsupportedEncodingException e) {
            throw new MyCollabException("Error", e);
        }
    }

    @Override
    public void checkLicenseInfo(byte[] licenseBytes, boolean isSave) {
        try {
            License license = new License();
            InputStream publicStream = LicenseResolverImpl.class.getClassLoader().getResourceAsStream("pubring.gpg");
            license.loadKeyRing(publicStream, null);
            license.setLicenseEncoded(new ByteArrayInputStream(licenseBytes), "UTF-8");
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            license.dumpLicense(outputStream);
            outputStream.flush();
            Properties prop = new Properties();
            byte[] bytes = outputStream.toByteArray();
            prop.load(new ByteArrayInputStream(bytes));
            licenseInfo = new LicenseInfo();
            licenseInfo.setCustomerId(prop.getProperty("customerId"));
            licenseInfo.setEdition(LicenseType.valueOf(prop.getProperty("edition")));
            licenseInfo.setExpireDate(DateTimeUtils.parseDateByW3C(prop.getProperty("expireDate")));
            licenseInfo.setIssueDate(DateTimeUtils.parseDateByW3C(prop.getProperty("issueDate")));
            licenseInfo.setLicenseOrg(prop.getProperty("licenseOrg"));
            if (isSave) {
                File licenseFile = getLicenseFile();
                FileOutputStream fileOutputStream = new FileOutputStream(licenseFile);
                fileOutputStream.write(licenseBytes);
                fileOutputStream.close();
            }
        } catch (IOException e) {
            LOG.warn("Can not read the license file mycollab.lic");
        } catch (PGPException e) {
            LOG.error("Error while reading license", e);
        }
    }


    @Override
    public LicenseInfo getLicenseInfo() {
        return licenseInfo;
    }
}
