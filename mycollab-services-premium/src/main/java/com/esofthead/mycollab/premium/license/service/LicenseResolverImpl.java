package com.esofthead.mycollab.premium.license.service;

import com.esofthead.mycollab.common.service.AppPropertiesService;
import com.esofthead.mycollab.core.MyCollabException;
import com.esofthead.mycollab.core.UserInvalidInputException;
import com.esofthead.mycollab.core.utils.DateTimeUtils;
import com.esofthead.mycollab.core.utils.FileUtils;
import com.esofthead.mycollab.license.LicenseInfo;
import com.esofthead.mycollab.license.LicenseResolver;
import com.esofthead.mycollab.license.LicenseType;
import com.verhas.licensor.License;
import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.joda.time.LocalDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.*;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Properties;

/**
 * @author MyCollab Ltd
 * @since 5.2.6
 */
@Service
public class LicenseResolverImpl implements LicenseResolver, InitializingBean {
    private static final Logger LOG = LoggerFactory.getLogger(LicenseResolverImpl.class);
    private LicenseInfo licenseInfo = null;

    @Autowired
    private AppPropertiesService appPropertiesService;

    @Override
    public void afterPropertiesSet() throws Exception {
        File licenseFile = getLicenseFile();
        if (licenseFile.isFile() && licenseFile.exists()) {
            byte[] licenseBytes = org.apache.commons.io.FileUtils.readFileToByteArray(licenseFile);
            checkLicenseInfo(licenseBytes, false);
        } else {
            acquireALicense();
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

    private void acquireALicense() {
        LOG.info("Acquire the trial license");
        DateTime startDate = new DateTime(appPropertiesService.getStartDate());
        DateTime now = new DateTime();
        int days = (int) new Duration(startDate, now).getStandardDays();
        if (days > 30) {
            licenseInfo = createTempValidLicense(30);
        } else {
            RestTemplate restTemplate = new RestTemplate();
            try {
                String licenseRequest = restTemplate.postForObject("https://api.mycollab.com/api/register-trial",
                        null, String.class);
                checkAndSaveLicenseInfo(licenseRequest);
            } catch (Exception e) {
                LOG.error("Can not retrieve a trial license", e);
                licenseInfo = createTempValidLicense(30 - days);
            }
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
            Date expireDate = DateTimeUtils.parseDateByW3C(prop.getProperty("expireDate"));
            LicenseInfo newLicenseInfo = new LicenseInfo();
            newLicenseInfo.setCustomerId(prop.getProperty("customerId"));
            newLicenseInfo.setLicenseType(LicenseType.valueOf(prop.getProperty("licenseType")));
            newLicenseInfo.setExpireDate(expireDate);
            newLicenseInfo.setIssueDate(DateTimeUtils.parseDateByW3C(prop.getProperty("issueDate")));
            newLicenseInfo.setLicenseOrg(prop.getProperty("licenseOrg"));
            newLicenseInfo.setMaxUsers(Integer.parseInt(prop.getProperty("maxUsers", "10")));
            if (isSave) {
                if (newLicenseInfo.isExpired()) {
                    throw new UserInvalidInputException("License is expired");
                }
                if (licenseInfo != null && newLicenseInfo.isTrial()) {
                    throw new UserInvalidInputException("You can not enter another trial license during trial period");
                }
                File licenseFile = getLicenseFile();
                FileOutputStream fileOutputStream = new FileOutputStream(licenseFile);
                fileOutputStream.write(licenseBytes);
                fileOutputStream.close();
            }
            licenseInfo = newLicenseInfo;
        } catch (Exception e) {
            licenseInfo = createInvalidLicense();
        }
    }

    private LicenseInfo createInvalidLicense() {
        LicenseInfo tmpLicenseInfo = new LicenseInfo();
        tmpLicenseInfo.setCustomerId("");
        tmpLicenseInfo.setExpireDate(new GregorianCalendar().getTime());
        tmpLicenseInfo.setIssueDate(new GregorianCalendar().getTime());
        tmpLicenseInfo.setLicenseOrg("");
        tmpLicenseInfo.setMaxUsers(1);
        tmpLicenseInfo.setLicenseType(LicenseType.INVALID);
        return tmpLicenseInfo;
    }

    private LicenseInfo createTempValidLicense(int days) {
        LocalDate now = new LocalDate();
        LicenseInfo tmpLicenseInfo = new LicenseInfo();
        tmpLicenseInfo.setCustomerId("");
        tmpLicenseInfo.setExpireDate(now.plusDays(days).toDate());
        tmpLicenseInfo.setIssueDate(now.toDate());
        tmpLicenseInfo.setLicenseOrg("");
        tmpLicenseInfo.setMaxUsers(10);
        tmpLicenseInfo.setLicenseType(LicenseType.PRO_TRIAL);
        return tmpLicenseInfo;
    }


    @Override
    public LicenseInfo getLicenseInfo() {
        return licenseInfo;
    }
}
