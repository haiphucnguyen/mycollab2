package com.mycollab.premium.license.service;

import com.mycollab.common.service.AppPropertiesService;
import com.mycollab.configuration.SiteConfiguration;
import com.mycollab.core.MyCollabException;
import com.mycollab.core.MyCollabVersion;
import com.mycollab.core.UserInvalidInputException;
import com.mycollab.core.utils.DateTimeUtils;
import com.mycollab.core.utils.FileUtils;
import com.mycollab.pro.license.LicenseInfo;
import com.mycollab.pro.license.LicenseType;
import com.verhas.licensor.License;
import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.*;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Properties;
import java.util.UUID;

/**
 * @author MyCollab Ltd
 * @since 5.2.6
 */
@Service
public class LicenseResolverImpl implements LicenseResolver, AppPropertiesService, InitializingBean {
    private static final Logger LOG = LoggerFactory.getLogger(LicenseResolverImpl.class);

    private Properties properties;
    private LicenseInfo licenseInfo = null;

    @Override
    public void afterPropertiesSet() throws Exception {
        File homeFolder = FileUtils.getHomeFolder();
        File sysFile = new File(homeFolder, ".app.properties");
        properties = new Properties();
        try {
            if (sysFile.isFile() && sysFile.exists()) {
                properties.load(new FileInputStream(sysFile));
                String startDate = properties.getProperty("startdate");
                if (startDate == null) {
                    properties.setProperty("startdate", DateTimeUtils.formatDateToW3C(new GregorianCalendar().getTime()));
                }
            } else {
                properties.setProperty("id", UUID.randomUUID().toString() + new LocalDateTime().getMillisOfSecond());
                properties.setProperty("startdate", DateTimeUtils.formatDateToW3C(new GregorianCalendar().getTime()));
            }
        } catch (IOException e) {
            LOG.error("Error", e);
        }

        File licenseFile = getLicenseFile();
        if (licenseFile.isFile() && licenseFile.exists()) {
            byte[] licenseBytes = org.apache.commons.io.FileUtils.readFileToByteArray(licenseFile);
            checkLicenseInfo(licenseBytes, false);
        } else {
            acquireALicense();
        }

        properties.store(new FileOutputStream(sysFile), "");
    }

    private File getLicenseFile() {
        File homeDir = FileUtils.getHomeFolder();
        File potentialOldFile = new File(homeDir, "mycollab.lic");
        File licenseFile = new File(homeDir, ".mycollab.lic");
        if (potentialOldFile.exists() && !licenseFile.exists()) {
            potentialOldFile.renameTo(licenseFile);
        }
        return licenseFile;
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
        DateTime startDate = new DateTime(getStartDate());
        DateTime now = new DateTime();
        int days = (int) new Duration(startDate, now).getStandardDays();
        String edition = properties.getProperty("edition", "Community");
        if (!getEdition().equals(edition)) {
            properties.setProperty("edition", getEdition());
            RestTemplate restTemplate = new RestTemplate();
            try {
                String licenseRequest = restTemplate.postForObject(SiteConfiguration.getApiUrl("order/register-trial"), null, String.class);
                checkAndSaveLicenseInfo(licenseRequest);
            } catch (Exception e) {
                LOG.error("Can not retrieve a trial license", e);
                licenseInfo = createTempValidLicense(30 - days);
            }
        } else {
            licenseInfo = createInvalidLicense();
        }
    }

    @Override
    public LicenseInfo checkLicenseInfo(byte[] licenseBytes, boolean isSave) {
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
            DateTime expireDate = new DateTime(DateTimeUtils.parseDateByW3C(prop.getProperty("expireDate")));
            if (MyCollabVersion.getReleasedDate() != null && MyCollabVersion.getReleasedDate().isAfter(expireDate)) {
                return createInvalidLicense();
            }
            LicenseInfo newLicenseInfo = new LicenseInfo();
            newLicenseInfo.setCustomerId(prop.getProperty("customerId"));
            newLicenseInfo.setLicenseType(LicenseType.valueOf(prop.getProperty("licenseType")));
            newLicenseInfo.setExpireDate(expireDate.toDate());
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
        return licenseInfo;
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

    @Override
    public String getSysId() {
        return properties.getProperty("id", UUID.randomUUID().toString() + new LocalDateTime().getMillisOfSecond());
    }

    @Override
    public Date getStartDate() {
        try {
            String dateValue = properties.getProperty("startdate");
            return DateTimeUtils.convertDateByString(dateValue, "yyyy-MM-dd'T'HH:mm:ss");
        } catch (Exception e) {
            return new GregorianCalendar().getTime();
        }
    }

    @Override
    public String getEdition() {
        return "Premium";
    }
}
